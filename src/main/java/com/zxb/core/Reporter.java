package com.zxb.core;

import java.util.List;
import java.util.Map;

public interface Reporter {

    void output(Map<String, RequestStat> dataSet);

}
