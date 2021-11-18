package com.amazing2j.amz.amz_server.utils.okhttp;

import com.alibaba.fastjson.JSON;
import com.amazing2j.amz.amz_server.exception.HTTPException;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class OkHttpUtil {

    private final static Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);

    public final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(8000, TimeUnit.MILLISECONDS)
            .readTimeout(8000, TimeUnit.MILLISECONDS)
            .build();

    private String request(String reqUrl, Request.Builder builder) throws IOException {
        long startTime = System.currentTimeMillis();
        String body = null;
        Response response = okHttpClient.newCall(builder.build()).execute();
        if (response.code() != 200) {
            throw new HTTPException(response.code(), response.message());
        }
        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            body = responseBody.string();
        }
        long endTime = System.currentTimeMillis();
        logger.info("{} cost time:{}", reqUrl.substring(reqUrl.lastIndexOf("/") + 1), (endTime - startTime));
        return body;
    }

    private String sendPostRequest(String reqUrl, Map<String, String> headers, Object data, String dataFormat) throws IOException {
        String bodyStr = JSON.toJSONString(data);
        Request.Builder requestBuilder = new Request.Builder().url(reqUrl);
        for (String key : headers.keySet()) {
            requestBuilder.addHeader(key, headers.get(key));
        }
        if (dataFormat == null) {
            dataFormat = "application/json";
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse(dataFormat), bodyStr);
        Request.Builder builder = requestBuilder.post(requestBody);
        return request(reqUrl, builder);
    }

    private String sendPutRequest(String reqUrl, Map<String, String> headers, Object data, String dataFormat) throws IOException {
        String bodyStr = JSON.toJSONString(data);
        Request.Builder requestBuilder = new Request.Builder().url(reqUrl);
        for (String key : headers.keySet()) {
            requestBuilder.addHeader(key, headers.get(key));
        }
        if (dataFormat == null) {
            dataFormat = "application/json";
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse(dataFormat), bodyStr);
        Request.Builder builder = requestBuilder.put(requestBody);
        return request(reqUrl, builder);
    }

    private String pckUrlParam(String reqUrl, Map<String, Object> params) {
        if (params == null) {
            return reqUrl;
        }
        String paraStr = null;
        for (String key : params.keySet()) {
            paraStr = String.format("%s=%s", key, params.get(key));
        }
        if (paraStr != null) {
            reqUrl = String.format("%s?%s", reqUrl, paraStr);
        }
        return reqUrl;
    }

    private String pckUrlRestParam(String reqUrl, Object[] params) {
        if (params == null) {
            return reqUrl;
        }
        ArrayList<String> ps = new ArrayList<>();
        for (Object p : params) {
            ps.add(p.toString());
        }
        return String.format("%s/%s", reqUrl, String.join("/", ps));
    }

    public String sendGetRequest(String reqUrl, Map<String, String> headers, Map<String, Object> params) throws IOException {
        reqUrl = pckUrlParam(reqUrl, params);
        Request.Builder requestBuilder = new Request.Builder().url(reqUrl);
        for (String key : headers.keySet()) {
            requestBuilder.addHeader(key, headers.get(key));
        }
        Request.Builder builder = requestBuilder.get();
        return request(reqUrl, builder);
    }

    public String sendGetRequestRest(String reqUrl, Map<String, String> headers, String[] params) throws IOException {
        reqUrl = pckUrlRestParam(reqUrl, params);
        Request.Builder requestBuilder = new Request.Builder().url(reqUrl);
        for (String key : headers.keySet()) {
            requestBuilder.addHeader(key, headers.get(key));
        }
        Request.Builder builder = requestBuilder.get();
        return request(reqUrl, builder);
    }

    public String sendPostRequestRestByJson(String reqUrl, Map<String, String> headers, Object[] params, Object data) throws IOException {
        reqUrl = pckUrlRestParam(reqUrl, params);
        return sendPostRequest(reqUrl, headers, data, "application/json");
    }

    public String sendPostRequestByJson(String reqUrl, Map<String, String> headers, Map<String, Object> params, Object data) throws IOException {
        reqUrl = pckUrlParam(reqUrl, params);
        return sendPostRequest(reqUrl, headers, data, "application/json");
    }

    public String sendPutRequestRestByJson(String reqUrl, Map<String, String> headers, Object[] params, Object data) throws IOException {
        reqUrl = pckUrlRestParam(reqUrl, params);
        return sendPutRequest(reqUrl, headers, data, "application/json");
    }

    public String sendPutRequestByJson(String reqUrl, Map<String, String> headers, Map<String, Object> params, Object data) throws IOException {
        reqUrl = pckUrlParam(reqUrl, params);
        return sendPutRequest(reqUrl, headers, data, "application/json");
    }
}
