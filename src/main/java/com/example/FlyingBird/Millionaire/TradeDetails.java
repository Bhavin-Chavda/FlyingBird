package com.example.FlyingBird.Millionaire;

import java.time.ZonedDateTime;

public class TradeDetails {

    private final ZonedDateTime entryTime;
    private final String type;
    private final double entryPrice;
    private final double stopLoss;
    private final double takeProfit;
    private final double crossoverOpen;
    private final double crossoverClose;
    private final double crossoverHigh;
    private final double crossoverLow;
    private ZonedDateTime exitTime;
    private String result;
    private double pnl;

    public TradeDetails(ZonedDateTime entryTime, String type, double entryPrice, double stopLoss, double takeProfit,
                        double crossoverOpen, double crossoverClose, double crossoverHigh, double crossoverLow) {
        this.entryTime = entryTime;
        this.type = type;
        this.entryPrice = entryPrice;
        this.stopLoss = stopLoss;
        this.takeProfit = takeProfit;
        this.crossoverOpen = crossoverOpen;
        this.crossoverClose = crossoverClose;
        this.crossoverHigh = crossoverHigh;
        this.crossoverLow = crossoverLow;
    }

    public ZonedDateTime getEntryTime() {
        return entryTime;
    }

    public String getType() {
        return type;
    }

    public double getEntryPrice() {
        return entryPrice;
    }

    public double getStopLoss() {
        return stopLoss;
    }

    public double getTakeProfit() {
        return takeProfit;
    }

    public double getCrossoverOpen() {
        return crossoverOpen;
    }

    public double getCrossoverClose() {
        return crossoverClose;
    }

    public double getCrossoverHigh() {
        return crossoverHigh;
    }

    public double getCrossoverLow() {
        return crossoverLow;
    }

    public ZonedDateTime getExitTime() {
        return exitTime;
    }

    public String getResult() {
        return result;
    }

    public double getPnl() {
        return pnl;
    }

    // Getters and setters for exitTime, result, pnl
    @Override
    public String toString() {
        return "TradeDetails{" +
                "entryTime=" + entryTime +
                ", type='" + type + '\'' +
                ", entryPrice=" + entryPrice +
                ", stopLoss=" + stopLoss +
                ", takeProfit=" + takeProfit +
                ", result='" + result + '\'' +
                ", pnl=" + pnl +
                '}';
    }
}
