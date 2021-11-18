package com.amazing2j.amz.utils.okhttp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

class OkHttpUtilTest {
    private final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJDb21wYW55Q29kZSI6IjE0MDEiLCJFeHAiOiIyMDIxLTExLTA5VDA3OjA3OjI2LjExMjQyNTA4OVoiLCJOZXciOmZhbHNlLCJTaWduZWRUaW1lIjoiMjAyMS0xMS0wMlQwNzowNzoyNi4xMTI0MjQ3MzRaIiwiVXNlck5vIjoiMTM4MTg2NDQ3NTAifQ.BxoSfpEnLNb9YM-_RVK18rNkDmqfGeuC-rUW71SvII8";

    @Test
    void sendGetRequest() {
        String reqUrl = "http://192.168.7.107:8383/api/user/current_user_info";
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", token);
        try {
            String respStr = new OkHttpUtil().sendGetRequest(reqUrl, headers, null);
            JSONObject jsonObject = JSON.parseObject(respStr);
            System.out.println(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void sendGetRequestRest() {
        String reqUrl = "http://192.168.7.107:8383/api/user/current_user_info";
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", token);
        try {
            String respStr = new OkHttpUtil().sendGetRequestRest(reqUrl, headers, null);
            JSONObject jsonObject = JSON.parseObject(respStr);
            System.out.println(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void sendPostRequestRestByJson() {
        String reqUrl = "http://192.168.7.107:8383/api/modeling/query/om_Product_Info";
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", token);

        HashMap<String, Integer> data = new HashMap<>();
        data.put("page", 0);
        data.put("page_size", 10);
        try {
            String respStr = new OkHttpUtil().sendPostRequestRestByJson(reqUrl, headers, null, data);
            JSONObject jsonObject = JSON.parseObject(respStr);
            System.out.println(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void sendPostRequestByJson() {
        String reqUrl = "http://192.168.7.107:8383/api/modeling/query/om_Product_Info";
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", token);

        HashMap<String, Integer> data = new HashMap<>();
        data.put("page", 0);
        data.put("page_size", 10);
        try {
            String respStr = new OkHttpUtil().sendPostRequestByJson(reqUrl, headers, null, data);
            JSONObject jsonObject = JSON.parseObject(respStr);
            System.out.println(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void sendPutRequestByJson() {
        String reqUrl = "http://192.168.7.107:8383/api/modeling/query/om_Product_Info";
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", token);

        HashMap<String, Integer> data = new HashMap<>();
        data.put("page", 0);
        data.put("page_size", 10);
        try {
            String respStr = new OkHttpUtil().sendPutRequestByJson(reqUrl, headers, null, data);
            JSONObject jsonObject = JSON.parseObject(respStr);
            System.out.println(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void sendPutRequestRestByJson() {
        String reqUrl = "http://192.168.7.107:8383/api/modeling/query/om_Product_Info";
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", token);

        HashMap<String, Integer> data = new HashMap<>();
        data.put("page", 0);
        data.put("page_size", 10);
        try {
            String respStr = new OkHttpUtil().sendPutRequestRestByJson(reqUrl, headers, null, data);
            JSONObject jsonObject = JSON.parseObject(respStr);
            System.out.println(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}