package com.example.FlyingBird.service;

import com.example.FlyingBird.dto.BacktestResults;
import com.example.FlyingBird.dto.TradeDetails;
import com.example.FlyingBird.interfaces.ReportGenerator;

import java.io.FileWriter;
import java.io.IOException;

public class CSVReportGenerator implements ReportGenerator {
    private final String outputPath;

    public CSVReportGenerator(String outputPath) {
        this.outputPath = outputPath;
    }

    @Override
    public void generateReport(BacktestResults results) {
        try (FileWriter writer = new FileWriter(outputPath)) {
            writer.write("Entry Time,Type,Entry Price,Stop Loss,Take Profit,Crossover Open,Crossover Close,Crossover High,Crossover Low,Result,PnL\n");
            for (TradeDetails trade : results.getTrades()) {
                writer.write(String.format(
                        "\"%s\",%s,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%s,%.2f\n",
                        trade.getEntryTime(),
                        trade.getType(),
                        trade.getEntryPrice(),
                        trade.getStopLoss(),
                        trade.getTakeProfit(),
                        trade.getCrossoverOpen(),
                        trade.getCrossoverClose(),
                        trade.getCrossoverHigh(),
                        trade.getCrossoverLow(),
                        trade.getResult(),
                        trade.getPnl()
                ));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

