package com.prometheus;

import java.time.LocalDateTime;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

public class PrometheusDemo {

    public static void main(String[] args) {
        query_range();
        System.out.println("");
        query();
    }

    /**
     * 访问区间的值(性能监控打开默认使用该方式查询，查询最近一小时的数据)
     * */
    public static void query_range() {
        //填写Prometheus的地址
        String prometheusUrl = "192.168.4.110:30909";
        //拼接好Prometheus查询语句
        String s = "kafka_server_replicamanager_isrexpands_total{pod=~'kafka0426-cluster-1-vrcgg|kafka0426-cluster-0-nw9qf|kafka0426-cluster-2-k229d',namespace='kafka0426'}";
        //只用修改上面的两个参数即可

        Map<String, String> param = Maps.newHashMap();
        param.put("query", s);
        LocalDateTime now = LocalDateTime.now();
        String endTime = String.valueOf(DateTimeUtils.localDateTimeToTimestamp(now)).substring(0, 10);
        String url = String.format("http://%s/api/v1/query_range", prometheusUrl);
        String startTime = getTimeByTimeType(now, "");
        param.put("start", startTime);
        param.put("end", endTime);
        param.put("step", "5");
        System.out.println(startTime+ "::::" + endTime);

        //生成完成时间戳  处理断开问题
        JSONObject jsonObject = OkHttpClientUtils.doGet(url, null, param);
        jsonObject = jsonObject.getJSONObject("data");
        System.out.println(jsonObject);
    }

    /**
     * 访问瞬时的方法(性能监控界面选择实时数据)
     * */
    public static void query() {
        //填写Prometheus的地址
        String prometheusUrl = "192.168.4.110:30909";
        //拼接好Prometheus查询语句
        String s = "kafka_server_replicamanager_isrexpands_total{pod=~'kafka0426-cluster-1-vrcgg|kafka0426-cluster-0-nw9qf|kafka0426-cluster-2-k229d',namespace='kafka0426'}";
        //只用修改上面的两个参数即可

        Map<String, String> param = Maps.newHashMap();
        param.put("query", s);
        String url = String.format("http://%s/api/v1/query", prometheusUrl);
        JSONObject jsonObject = OkHttpClientUtils.doGet(url, null, param);
        System.out.println(jsonObject);
    }


    public static String getTimeByTimeType(LocalDateTime now, String timeType) {
        String startTime = "";
        switch (timeType) {
            case "h":
                long minusHours = DateTimeUtils.minusHours(now, 1);
                startTime = String.valueOf(minusHours).substring(0, 10);
                break;
            case "d":
                long minusDays = DateTimeUtils.minusDays(now, 1);
                startTime = String.valueOf(minusDays).substring(0, 10);
                break;
            case "w":
                long minusWeeks = DateTimeUtils.minusWeeks(now, 1);
                startTime = String.valueOf(minusWeeks).substring(0, 10);
                break;
            case "m":
                long minusMonths = DateTimeUtils.minusMonths(now, 1);
                startTime = String.valueOf(minusMonths).substring(0, 10);
                break;
            default:
                long defaultMinusHours = DateTimeUtils.minusHours(now, 1);
                startTime = String.valueOf(defaultMinusHours).substring(0, 10);
        }
        return startTime;
    }
}
