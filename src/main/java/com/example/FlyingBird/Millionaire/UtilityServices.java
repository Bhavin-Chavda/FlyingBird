package com.example.FlyingBird.Millionaire;

import java.util.List;

public class UtilityServices {

    public void updateEMACrossing(List<Candle> candleList)
    {
        for(Candle candle : candleList)
        {
            if(candle.getOpen() < candle.getEma50() && candle.getClose() > candle.getEma50())
            {
                candle.setCrossed(true);
                candle.setCrosstype(CROSSTYPE.BULLISH);
            }
            else if(candle.getOpen() > candle.getEma50() && candle.getClose() < candle.getEma50())
            {
                candle.setCrossed(true);
                candle.setCrosstype(CROSSTYPE.BEARISH);
            }
        }
    }

}
