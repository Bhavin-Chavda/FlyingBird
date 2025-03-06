package com.example.FlyingBird.interfaces;

import com.example.FlyingBird.dto.Candle;

import java.util.List;

public interface DataFetcher {
    List<Candle> fetchHistoricalData(String symbol, String interval, String startDate, String endDate) throws Exception;
}
