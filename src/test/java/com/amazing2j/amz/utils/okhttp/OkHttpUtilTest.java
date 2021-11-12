package com.amazing2j.amz.utils.okhttp;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class OkHttpUtilTest {

    @Test
    public void TestSendPostByJson() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJDb21wYW55Q29kZSI6IjE0MDEiLCJFeHAiOiIyMDIxLTExLTA5VDA3OjA3OjI2LjExMjQyNTA4OVoiLCJOZXciOmZhbHNlLCJTaWduZWRUaW1lIjoiMjAyMS0xMS0wMlQwNzowNzoyNi4xMTI0MjQ3MzRaIiwiVXNlck5vIjoiMTM4MTg2NDQ3NTAifQ.BxoSfpEnLNb9YM-_RVK18rNkDmqfGeuC-rUW71SvII8";
        String reqUrl = "http://192.168.7.107:8383/api/user/current_user_info";
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", token);
        try {
            byte[] respStr = new OkHttpUtil().sendPostByJson(reqUrl, headers, new Object());
            JSON.parseObject(respStr);
            System.out.println(respStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}