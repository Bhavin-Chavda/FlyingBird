package com.example.FlyingBird.Millionaire;

import java.util.ArrayList;
import java.util.List;

public class BacktestResults {

    private List<TradeDetails> trades;

    public BacktestResults() {
        this.trades = new ArrayList<>();
    }

    public void setTrades(List<TradeDetails> trades) {
        this.trades = trades;
    }

    public List<TradeDetails> getTrades() {
        return trades;
    }

}
