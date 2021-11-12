package com.amazing2j.amz.utils.okhttp;

import org.springframework.stereotype.Component;

@Component
public class HttpHeader {
    private String contentType;

    private String Authorization;

    public HttpHeader() {
    }

    public HttpHeader(String contentType, String authorization) {
        this.contentType = contentType;
        Authorization = authorization;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }
}
