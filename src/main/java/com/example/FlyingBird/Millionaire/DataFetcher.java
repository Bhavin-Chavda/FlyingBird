package com.example.FlyingBird.Millionaire;

import java.util.List;

public interface DataFetcher {
    List<Candle> fetchHistoricalData(String symbol, String interval, String startDate, String endDate) throws Exception;
}
