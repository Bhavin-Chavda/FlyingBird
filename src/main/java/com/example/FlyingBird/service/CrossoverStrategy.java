package com.example.FlyingBird.service;

import com.example.FlyingBird.dto.BacktestResults;
import com.example.FlyingBird.dto.Candle;
import com.example.FlyingBird.dto.TradeDetails;
import com.example.FlyingBird.interfaces.Strategy;

import java.util.ArrayList;
import java.util.List;

public class CrossoverStrategy implements Strategy
{

    private final int cooldownPeriod;
    private final double riskRewardRatio;

    public CrossoverStrategy(int cooldownPeriod, double riskRewardRatio)
    {
        this.cooldownPeriod = cooldownPeriod;
        this.riskRewardRatio = riskRewardRatio;
    }

    @Override
    public BacktestResults executeStrategy(List<Candle> candles)
    {

            BacktestResults results = new BacktestResults();
            List<TradeDetails> trades = new ArrayList<>();
            double equity = 10000.0;

            for (int i = cooldownPeriod; i < candles.size(); i++) {
                Candle current = candles.get(i);
                Candle prev = candles.get(i - 1);
                Candle prePrev = candles.get(i - 2);

                // Detect crossovers
                boolean bullish = prev.getClose() > prev.getEma50() && prePrev.getClose() < prePrev.getEma50();
                boolean bearish = prev.getClose() < prev.getEma50() && prePrev.getClose() > prePrev.getEma50();

                if (bullish && current.getHigh() > prev.getHigh()) {
                    double entry = current.getHigh();
                    double sl = Math.min(prev.getLow(), prePrev.getLow());
                    double tp = entry + (entry - sl) * riskRewardRatio;

                    TradeDetails trade = new TradeDetails(
                            current.getTimestamp(),
                            "BUY",
                            entry,
                            sl,
                            tp,
                            prev.getOpen(),
                            prev.getClose(),
                            prev.getHigh(),
                            prev.getLow()
                    );

                    trades.add(trade);
                    System.out.println("Created BUY trade: " + trade);
                } else if (bearish && current.getLow() < prev.getLow()) {
                    double entry = current.getLow();
                    double sl = Math.max(prev.getHigh(), prePrev.getHigh());
                    double tp = entry - (sl - entry) * riskRewardRatio;

                    TradeDetails trade = new TradeDetails(
                            current.getTimestamp(),
                            "SELL",
                            entry,
                            sl,
                            tp,
                            prev.getOpen(),
                            prev.getClose(),
                            prev.getHigh(),
                            prev.getLow()
                    );

                    trades.add(trade);
                    System.out.println("Created SELL trade: " + trade);
                }
            }

            results.setTrades(trades);
            return results;
    }
}

