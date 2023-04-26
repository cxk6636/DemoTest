package com.prometheus;


import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.hutool.core.collection.CollUtil;
import okhttp3.ConnectionPool;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpClientUtils {

    private static final Logger logger = LoggerFactory.getLogger(OkHttpClientUtils.class);

    public static final long connectTimeout = 2L; // 秒
    public static final long readTimeout = 5L; // 秒
    public static final long writeTimeout = 5L; // 秒
    public static final String charset = "UTF-8";

    private static OkHttpClient okHttpClient;
    private static ConnectionPool connectionPool;

    static {
        connectionPool = new ConnectionPool(200, 5, TimeUnit.MINUTES);
        okHttpClient = createOkHttpClient();
    }

    /**
     * 创建OkHttpClient对象
     *
     * @return
     */
    public static OkHttpClient createOkHttpClient() {
        final X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[] { trustManager }, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            return new OkHttpClient().newBuilder().connectionPool(connectionPool)
                    .connectTimeout(connectTimeout, TimeUnit.SECONDS)// 连接超时
                    .readTimeout(readTimeout, TimeUnit.SECONDS).writeTimeout(writeTimeout, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true).sslSocketFactory(sslSocketFactory, trustManager)// 配置
                    .hostnameVerifier((hostname, session) -> true).build();
        } catch (Exception e) {
            logger.error("创建OkHttpClient失败:{}", e);
            throw new RuntimeException(e);
        }
    }

    public static JSONObject doPost(String url, Map<String, String> headers, Map<String, String> param) {
        FormBody.Builder formbody = new FormBody.Builder();
        if (CollUtil.isNotEmpty(param)) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                formbody.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody body = formbody.build();
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (CollUtil.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        requestBuilder.addHeader("token", BaseContextHandler.getToken());
        requestBuilder.addHeader("refreshToken", BaseContextHandler.getRefreshToken());
        Request request = requestBuilder.post(body).build();
        Response response = null;
        String result = "";
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.body() != null) {
                result = response.body().string();
            }
        } catch (Exception e) {
            logger.error("请求地址:{}, 错误信息:{}", url, e.getMessage());
            return null;
        } finally {
            if (response != null) {
                response.close();
            }
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(result);
        } catch (Exception e) {
            logger.error("请求地址:{}, 格式化参数错误:{}", url, result);
            return null;
        }
        return jsonObject;
    }

    public static JSONObject doPost(String url, Map<String, String> headers, String content) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), content);
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (CollUtil.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        requestBuilder.addHeader("token", BaseContextHandler.getToken());
        requestBuilder.addHeader("refreshToken", BaseContextHandler.getRefreshToken());
        Request request = requestBuilder.post(requestBody).build();
        Response response = null;
        String result = "";
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.body() != null) {
                result = response.body().string();
            }
        } catch (Exception e) {
            logger.error("请求地址:{}, 错误信息:{}", url, e.getMessage());
            return null;
        } finally {
            if (response != null) {
                response.close();
            }
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(result);
        } catch (Exception e) {
            logger.error("请求地址:{}, 格式化参数错误:{}", url, result);
            return null;
        }
        return jsonObject;
    }

    public static JSONObject doPatch(String url, Map<String, String> headers, String content) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/merge-patch+json"), content);
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (CollUtil.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        requestBuilder.addHeader("token", BaseContextHandler.getToken());
        requestBuilder.addHeader("refreshToken", BaseContextHandler.getRefreshToken());
        Request request = requestBuilder.patch(requestBody).build();
        Response response = null;
        String result = "";
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.body() != null) {
                result = response.body().string();
            }
        } catch (Exception e) {
            logger.error("请求地址:{}, 错误信息:{}", url, e.getMessage());
            return null;
        } finally {
            if (response != null) {
                response.close();
            }
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(result);
        } catch (Exception e) {
            logger.error("请求地址:{}, 格式化参数错误:{}", url, result);
            return null;
        }
        return jsonObject;
    }

    public static JSONObject doGet(String url, Map<String, String> headers, Map<String, String> param) {
        if (CollUtil.isNotEmpty(param)) {
            List<NameValuePair> parameters = new ArrayList<>();
            for (Map.Entry<String, String> entry : param.entrySet()) {
                String key = entry.getKey();
                parameters.add(new BasicNameValuePair(key, param.get(key)));
            }
            String params = URLEncodedUtils.format(parameters, charset);
            url = url + "?" + params;
        }
        Request.Builder requestBuilder = new Request.Builder();
        if (CollUtil.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        requestBuilder.addHeader("token", BaseContextHandler.getToken());
        requestBuilder.addHeader("refreshToken", BaseContextHandler.getRefreshToken());
        Request request = requestBuilder.url(url).build();
        Response response = null;
        String result = "";
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.body() != null) {
                result = response.body().string();
            }
        } catch (Exception e) {
            logger.error("请求地址:{}, 错误信息:{}", url, e.getMessage());
            return null;
        } finally {
            if (response != null) {
                response.close();
            }
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(result);
        } catch (Exception e) {
            logger.error("请求地址:{}, 格式化参数错误:{}", url, result);
            return null;
        }
        return jsonObject;
    }

    public static String doGetString(String url, Map<String, String> headers, Map<String, String> param) {
        if (CollUtil.isNotEmpty(param)) {
            List<NameValuePair> parameters = new ArrayList<>();
            for (Map.Entry<String, String> entry : param.entrySet()) {
                String key = entry.getKey();
                parameters.add(new BasicNameValuePair(key, param.get(key)));
            }
            String params = URLEncodedUtils.format(parameters, charset);
            url = url + "?" + params;
        }
        Request.Builder requestBuilder = new Request.Builder();
        if (CollUtil.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        requestBuilder.addHeader("token", BaseContextHandler.getToken());
        requestBuilder.addHeader("refreshToken", BaseContextHandler.getRefreshToken());
        Request request = requestBuilder.url(url).build();
        Response response = null;
        String result = "";
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.body() != null) {
                result = response.body().string();
            }
        } catch (Exception e) {
            logger.error("请求地址:{}, 错误信息:{}", url, e.getMessage());
            return null;
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return result;
    }

}
