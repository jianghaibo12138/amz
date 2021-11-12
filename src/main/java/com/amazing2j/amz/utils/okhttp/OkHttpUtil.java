package com.amazing2j.amz.utils.okhttp;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class OkHttpUtil {

    private final static Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);

    public final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(8000, TimeUnit.MILLISECONDS)
            .readTimeout(8000, TimeUnit.MILLISECONDS)
            .build();

    private byte[] sendPostRequest(String reqUrl, Request.Builder builder) throws IOException {
        long startTime = System.currentTimeMillis();
        byte[] body = new byte[]{};
        Response response = okHttpClient.newCall(builder.build()).execute();
        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            body = responseBody.bytes();
        }
        long endTime = System.currentTimeMillis();
        logger.info("{} cost time:{}", reqUrl.substring(reqUrl.lastIndexOf("/") + 1), (endTime - startTime));
        return body;
    }

    public byte[] sendPostByJson(String reqUrl, Map<String, String> headers, Object param) throws IOException {
        String bodyStr = JSON.toJSONString(param);
        Request.Builder requestBuilder = new Request.Builder().url(reqUrl);
        for (String key : headers.keySet()) {
            requestBuilder.addHeader(key, headers.get(key));
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), bodyStr);
        Request.Builder builder = requestBuilder.post(requestBody);
        return sendPostRequest(reqUrl, builder);
    }
}
