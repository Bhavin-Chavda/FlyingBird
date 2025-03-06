package com.example.FlyingBird.Millionaire;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BinanceDataFetcher implements DataFetcher {
    @Override
    public List<Candle> fetchHistoricalData(String symbol, String interval, String startDate, String endDate) throws Exception {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parse start and end dates
        LocalDate startLocalDate = LocalDate.parse(startDate, dateFormatter);
        LocalDate endLocalDate = LocalDate.parse(endDate, dateFormatter);

        // Convert to ZonedDateTime with proper end time
        ZonedDateTime startZdt = startLocalDate.atStartOfDay(ZoneId.of("Asia/Kolkata"));
        ZonedDateTime endZdt = endLocalDate.atTime(LocalTime.of(23, 59, 59)).atZone(ZoneId.of("Asia/Kolkata"));

        // Convert to UTC epoch milliseconds
        long startTimestamp = startZdt.toInstant().toEpochMilli();
        long endTimestamp = endZdt.toInstant().toEpochMilli();

        System.out.println("Fetching data from: " + startZdt + " to " + endZdt);
        System.out.println("Start Timestamp: " + startTimestamp);
        System.out.println("End Timestamp: " + endTimestamp);
        System.out.println("Current Time (Millis): " + System.currentTimeMillis());

        List<Candle> candles = new ArrayList<>();
        long currentStart = startTimestamp;

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            while (currentStart < endTimestamp) {  // Changed from <= to <

                String url = String.format(
                        "https://api.binance.com/api/v3/klines?symbol=%s&interval=%s&limit=1000&startTime=%d",
                        symbol, interval, currentStart
                );

                HttpGet request = new HttpGet(url);
                try (CloseableHttpResponse response = client.execute(request)) {
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayNode data = (ArrayNode) mapper.readTree(response.getEntity().getContent());

                    if (data.size() == 0) break; // Stop if no data is returned

                    long lastTimestamp = -1;
                    for (JsonNode candleData : data) {
                        long timestamp = candleData.get(0).longValue();

                        ZonedDateTime zdt = ZonedDateTime.ofInstant(
                                Instant.ofEpochMilli(timestamp),
                                ZoneId.of("UTC")
                        ).withZoneSameInstant(ZoneId.of("Asia/Kolkata"));

                        // Round to 2 decimal places
                        double open = candleData.get(1).asDouble();
                        double high = candleData.get(2).asDouble();
                        double low = candleData.get(3).asDouble();
                        double close = candleData.get(4).asDouble();
                        double volume = candleData.get(5).asDouble();

                        Candle candle = new Candle(zdt, open, high, low, close, volume);

                        // Stop if Binance returns data beyond the expected endDate
                        if (zdt.isAfter(endZdt)) {
                            System.out.println("Stopping: Last fetched candle exceeds end date. " + zdt);
                            return candles;
                        }

                        candles.add(candle);
                        lastTimestamp = timestamp;
                    }

                    if (lastTimestamp == -1) break;
                    currentStart = lastTimestamp + 1;
                }
            }
        }

        return candles;
    }
}