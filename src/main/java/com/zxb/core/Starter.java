package com.zxb.core;

import java.io.IOException;
import java.util.Map;

public class Starter {

    private Reporter reporter = null;
    private Statistics statistics = null;
    private Measure measure = null;

    public static void main(String[] args) throws Exception {
        String url = "http://www.baidu.com";
        Starter starter = new Starter(url);
        starter.start();
    }

    public Starter(String url) throws Exception {
        measure = new MeasureImpl(url);
        statistics = new Statistics();
        reporter = new ConsoleReporter();
    }

    public void start() throws IOException, InterruptedException {
        measure.execute(10, 100);
        reporter.output(statistics.aggregate(measure.getResult()));
    }

}
