package com.example.FlyingBird.Millionaire;

import java.util.List;

public interface IndicatorCalculator {
    List<Candle> calculateEMA(List<Candle> candles);

}
