package com.example.FlyingBird.interfaces;

import com.example.FlyingBird.dto.BacktestResults;
import com.example.FlyingBird.dto.Candle;

import java.util.List;

public interface Strategy {
    BacktestResults executeStrategy(List<Candle> candles);
}
