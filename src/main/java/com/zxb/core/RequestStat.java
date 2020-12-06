package com.zxb.core;

public class RequestStat {

    private double responseTimeAverage = 0.0;
    private double responseTime95 = 0.0;

    public void setResponseTimeAverage(double responseTimeAverage) {
        this.responseTimeAverage = responseTimeAverage;
    }

    public void setResponseTime95(double responseTime95) {
        this.responseTime95 = responseTime95;
    }

    public double getResponseTimeAverage() {
        return responseTimeAverage;
    }

    public double getResponseTime95() {
        return responseTime95;
    }

}
