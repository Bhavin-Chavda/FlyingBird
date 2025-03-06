package com.example.FlyingBird.Millionaire;

import java.util.List;

public interface Strategy {
    BacktestResults executeStrategy(List<Candle> candles);
}
