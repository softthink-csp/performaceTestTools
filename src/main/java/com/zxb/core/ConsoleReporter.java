package com.zxb.core;

import java.util.List;
import java.util.Map;

public class ConsoleReporter implements Reporter {

    @Override
    public void output(Map<String, RequestStat> data) {
        for (Map.Entry<String, RequestStat> entry : data.entrySet()) {
            System.out.print(entry.getKey() + " ");
            RequestStat requestStat = entry.getValue();
            System.out.print(requestStat.getResponseTimeAverage() + " ");
            System.out.print(requestStat.getResponseTime95() + " ");
            System.out.println();
        }
    }

}
