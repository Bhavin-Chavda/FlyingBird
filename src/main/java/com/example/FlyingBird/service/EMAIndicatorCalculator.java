package com.example.FlyingBird.service;

import com.example.FlyingBird.dto.Candle;
import com.example.FlyingBird.interfaces.IndicatorCalculator;

import java.util.List;

public class EMAIndicatorCalculator implements IndicatorCalculator {
    @Override
    public List<Candle> calculateEMA(List<Candle> candles) {
        calculateEMA50(candles);
        return candles;
    }

    private void calculateEMA50(List<Candle> candles) {
        double[] closes = new double[candles.size()];
        for (int i = 0; i < candles.size(); i++) {
            closes[i] = candles.get(i).getClose();
        }

        double[] ema = new double[candles.size()];
        ema[0] = closes[0];
        for (int i = 1; i < closes.length; i++) {
            ema[i] = (closes[i] * (2.0 / 51)) + (ema[i - 1] * (1 - (2.0 / 51)));
        }

        for (int i = 0; i < candles.size(); i++) {
            // Round off to two decimal places
            double roundedEMA = Math.round(ema[i] * 100.0) / 100.0;
            candles.get(i).setEma50(roundedEMA);
        }
    }
}
