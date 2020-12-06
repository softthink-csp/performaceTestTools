package com.zxb.core;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Measure {

    void execute(int connectionCount, int requestCount) throws InterruptedException, IOException;

    Map<String, List<Long>> getResult();

}
