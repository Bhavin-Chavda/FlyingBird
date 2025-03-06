package com.example.FlyingBird.interfaces;

import com.example.FlyingBird.dto.Candle;

import java.util.List;

public interface IndicatorCalculator {
    List<Candle> calculateEMA(List<Candle> candles);

}
