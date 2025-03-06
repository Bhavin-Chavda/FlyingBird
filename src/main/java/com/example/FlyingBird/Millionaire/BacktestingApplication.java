package com.example.FlyingBird.Millionaire;

import java.util.List;

public class BacktestingApplication  {

    public static void main(String[] args) {

        UtilityServices utilityServices = new UtilityServices();

        // Configuration
        BacktestConfig config = new BacktestConfig(
                "BTCUSDT",
                "15m",
                "2022-01-01",
                "2025-03-03",
                10,
                2.0,
                "backtest_report_final_java.csv"
        );

        // Component initialization
        DataFetcher dataFetcher = new BinanceDataFetcher();
        IndicatorCalculator indicatorCalculator = new EMAIndicatorCalculator();
//        Strategy strategy = new CrossoverStrategy(config.getCooldownPeriod(), config.getRiskRewardRatio());
//        ReportGenerator reportGenerator = new CSVReportGenerator(config.getReportPath());

        try {
            // Data pipeline
            List<Candle> rawCandles = dataFetcher.fetchHistoricalData(
                    config.getSymbol(),
                    config.getInterval(),
                    config.getStartDate(),
                    config.getEndDate()
            );

            rawCandles = indicatorCalculator.calculateEMA(rawCandles);
            utilityServices.updateEMACrossing(rawCandles);
            for(Candle candle : rawCandles)
            {
                System.out.println(candle);
            }
            System.out.println("List size fo canldles is : "+rawCandles.size());

//            BacktestResults results = strategy.executeStrategy(processedCandles);
//
//            // Reporting
//            reportGenerator.generateReport(results);
            System.out.println("Backtesting completed successfully!");
        } catch (Exception e) {
            System.err.println("Error during backtesting: " + e.getMessage());
        }
    }
}