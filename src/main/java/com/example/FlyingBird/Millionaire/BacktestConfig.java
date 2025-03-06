package com.example.FlyingBird.Millionaire;

public class BacktestConfig {

    private final String symbol;
    private final String interval;
    private final String startDate;
    private final String endDate;
    private final int cooldownPeriod;
    private final double riskRewardRatio;
    private final String reportPath;


    public BacktestConfig(String symbol, String interval, String startDate, String endDate, int cooldownPeriod, double riskRewardRatio, String reportPath) {
        this.symbol = symbol;
        this.interval = interval;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cooldownPeriod = cooldownPeriod;
        this.riskRewardRatio = riskRewardRatio;
        this.reportPath = reportPath;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getInterval() {
        return interval;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getCooldownPeriod() {
        return cooldownPeriod;
    }

    public double getRiskRewardRatio() {
        return riskRewardRatio;
    }

    public String getReportPath() {
        return reportPath;
    }
}
