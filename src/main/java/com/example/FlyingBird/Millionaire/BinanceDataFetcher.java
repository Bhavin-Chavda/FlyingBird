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

        // Create ZonedDateTime with time zone and time components
        ZonedDateTime startZdt = startLocalDate.atStartOfDay(ZoneId.of("Asia/Kolkata"));
        ZonedDateTime endZdt = endLocalDate.atTime(LocalTime.MAX).atZone(ZoneId.of("Asia/Kolkata"));

        // Convert to UTC epoch milliseconds
        long startTimestamp = startZdt.toInstant().toEpochMilli();
        long endTimestamp = endZdt.toInstant().toEpochMilli();

        List<Candle> candles = new ArrayList<>();
        long currentStart = startTimestamp;

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            while (currentStart <= endTimestamp) {
                String url = String.format(
                        "https://api.binance.com/api/v3/klines?symbol=%s&interval=%s&limit=1000&startTime=%d",
                        symbol, interval, currentStart
                );

                HttpGet request = new HttpGet(url);
                try (CloseableHttpResponse response = client.execute(request)) {
                    ObjectMapper mapper = new ObjectMapper();
                    ArrayNode data = (ArrayNode) mapper.readTree(response.getEntity().getContent());

                    if (data.size() == 0) break;

                    long lastTimestamp = -1;
                    for (JsonNode candleData : data) {
//                        System.out.println(candleData);
                        long timestamp = candleData.get(0).longValue();





                        ZonedDateTime zdt = ZonedDateTime.ofInstant(
                                Instant.ofEpochMilli(timestamp),
                                ZoneId.of("UTC")
                        ).withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
//                        System.out.println(zdt+" "+candleData.get(1)+" "+candleData.get(2)+" "+candleData.get(3)+" "+candleData.get(4)+" "+candleData.get(5)+" ");

                        // Round to 2 decimal places
                        double open = candleData.get(1).asDouble();
                        double high = candleData.get(2).asDouble();
                        double low = candleData.get(3).asDouble();
                        double close = candleData.get(4).asDouble();
                        double volume = candleData.get(5).asDouble();

//                        System.out.println(open+" "+high+" "+low+" "+close+" "+volume+" ");

                        candles.add(new Candle(zdt, open, high, low, close, volume));
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