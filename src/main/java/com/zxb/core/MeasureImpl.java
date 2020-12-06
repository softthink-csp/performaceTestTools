package com.zxb.core;



import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MeasureImpl implements Measure {

    private Map<String, List<Long>> dataSet = null;
    private String url;

    public MeasureImpl(String url) throws Exception {
        this.url = url;
        dataSet = new HashMap<String, List<Long>>();

    }

    private HttpClient getConnection() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        return httpClient;
    }

    @Override
    public void execute(int connectionCount, int requestCount) throws InterruptedException, IOException {
        ExecutorService executors = Executors.newFixedThreadPool(connectionCount);
        CountDownLatch countDownLatch = new CountDownLatch(connectionCount);
        for (int i = 0; i < connectionCount; i++) {

            executors.execute(() -> {
                String currentThreadName = Thread.currentThread().getName();
                List<Long> data = new ArrayList<Long>();
                dataSet.put(currentThreadName, data);
                try {
                    HttpClient client = getConnection();
                    for (int j = 0; j < requestCount; j++) {
                        long startTime = System.currentTimeMillis();
                        requestPost(client);
                        data.add(System.currentTimeMillis() - startTime);
                    }
                    countDownLatch.countDown();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
        countDownLatch.await();
    }

    private void requestPost(HttpClient client) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httpPost);
        response.close();
    }

    @Override
    public Map<String, List<Long>> getResult() {
        return dataSet;
    }

}
