package com.example.FlyingBird.interfaces;

import com.example.FlyingBird.dto.BacktestResults;

import java.io.IOException;

public interface ReportGenerator {
    void generateReport(BacktestResults results) throws IOException;
}
