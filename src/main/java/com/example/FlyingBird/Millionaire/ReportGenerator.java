package com.example.FlyingBird.Millionaire;

import java.io.IOException;

public interface ReportGenerator {
    void generateReport(BacktestResults results) throws IOException;
}
