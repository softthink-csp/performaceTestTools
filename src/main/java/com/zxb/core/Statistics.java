package com.zxb.core;

import java.util.*;

public class Statistics {

    public Map<String, RequestStat> aggregate(Map<String, List<Long>> requestInfo) {
        Map<String, RequestStat> requestStats = new HashMap<String, RequestStat>();
        for (Map.Entry<String, List<Long>> entry : requestInfo.entrySet()) {
            RequestStat requestStat = new RequestStat();
            List<Long> dataSet = entry.getValue();
            requestStat.setResponseTimeAverage(responseTimeAverage(dataSet));
            requestStat.setResponseTime95(responseTime95(dataSet));
            requestStats.put(entry.getKey(), requestStat);
        }
        return requestStats;
    }

    private Double responseTimeAverage(List<Long> data) {
        return getAverage(data, data.size());
    }

    private Double responseTime95(List<Long> data) {
        int size = data.size();
        List<Long> clone = new ArrayList<Long>(data);
        clone.sort(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return 01 - 02;
            }
        });
        int size95 = size * 95 / 100;
        return getAverage(clone, size95);
    }

    private double getAverage(List<Long> dataSet, int length) {
        long sum = 0l;
        for (int i = 0; i < length; i++) {
            sum += dataSet.get(i);
        }
        return (double)sum / length;
    }

}
