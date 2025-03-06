package com.example.FlyingBird.Millionaire;

import java.time.ZonedDateTime;

public class Candle {

    private final ZonedDateTime timestamp;
    private final double open, high, low, close, volume;
    private double ema50;
    private boolean crossed;
    private CROSSTYPE crosstype;




    public Candle(ZonedDateTime timestamp, double open, double high, double low, double close, double volume) {
        this.timestamp = timestamp;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.crossed = false;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public boolean getCrossed() {
        return crossed;
    }

    public void setCrossed(boolean crossed) {
        this.crossed = crossed;
    }

    public double getVolume() {
        return volume;
    }

    public double getEma50() {
        return ema50;
    }

    public void setEma50(double ema50) {
        this.ema50 = ema50;
    }

    public boolean isCrossed() {
        return crossed;
    }

    public CROSSTYPE getCrosstype() {
        return crosstype;
    }

    public void setCrosstype(CROSSTYPE crosstype) {
        this.crosstype = crosstype;
    }

    @Override
    public String toString() {
        return "Candle{" +
                "timestamp=" + timestamp +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                ", ema50=" + ema50 +
                ", crossed=" + crossed +
                ", crosstype=" + crosstype +
                '}';
    }
}
