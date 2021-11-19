package com.amazing2j.amz.amz_client.model.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class TCCRegisterPayload {

    @JSONField(name = "try_url")
    private String tryUrl;

    @JSONField(name = "try_method")
    private String tryMethod;

    @JSONField(name = "try_rest")
    private Integer tryRest;

    @JSONField(name = "try_body")
    private String tryBody;

    @JSONField(name = "try_header")
    private String tryHeader;

    @JSONField(name = "confirm_url")
    private String confirmUrl;

    @JSONField(name = "confirm_method")
    private String confirmMethod;

    @JSONField(name = "confirm_rest")
    private Integer confirmRest;

    @JSONField(name = "confirm_body")
    private String confirmBody;

    @JSONField(name = "confirm_header")
    private String confirmHeader;

    @JSONField(name = "cancel_url")
    private String cancelUrl;

    @JSONField(name = "cancel_method")
    private String cancelMethod;

    @JSONField(name = "cancel_rest")
    private Integer cancelRest;

    @JSONField(name = "cancel_body")
    private String cancelBody;

    @JSONField(name = "cancel_header")
    private String cancelHeader;

    @JSONField(name = "callback_url")
    private String callbackUrl;

    @JSONField(name = "callback_header")
    private String callbackHeader;

    public Integer getTryRest() {
        return tryRest;
    }

    public void setTryRest(Integer tryRest) {
        this.tryRest = tryRest;
    }

    public Integer getConfirmRest() {
        return confirmRest;
    }

    public void setConfirmRest(Integer confirmRest) {
        this.confirmRest = confirmRest;
    }

    public Integer getCancelRest() {
        return cancelRest;
    }

    public void setCancelRest(Integer cancelRest) {
        this.cancelRest = cancelRest;
    }

    public String getTryMethod() {
        return tryMethod;
    }

    public void setTryMethod(String tryMethod) {
        this.tryMethod = tryMethod;
    }

    public String getConfirmMethod() {
        return confirmMethod;
    }

    public void setConfirmMethod(String confirmMethod) {
        this.confirmMethod = confirmMethod;
    }

    public String getCancelMethod() {
        return cancelMethod;
    }

    public void setCancelMethod(String cancelMethod) {
        this.cancelMethod = cancelMethod;
    }

    public String getTryUrl() {
        return tryUrl;
    }

    public void setTryUrl(String tryUrl) {
        this.tryUrl = tryUrl;
    }

    public String getTryBody() {
        return tryBody;
    }

    public void setTryBody(String tryBody) {
        this.tryBody = tryBody;
    }

    public String getTryHeader() {
        return tryHeader;
    }

    public void setTryHeader(String tryHeader) {
        this.tryHeader = tryHeader;
    }

    public String getConfirmUrl() {
        return confirmUrl;
    }

    public void setConfirmUrl(String confirmUrl) {
        this.confirmUrl = confirmUrl;
    }

    public String getConfirmBody() {
        return confirmBody;
    }

    public void setConfirmBody(String confirmBody) {
        this.confirmBody = confirmBody;
    }

    public String getConfirmHeader() {
        return confirmHeader;
    }

    public void setConfirmHeader(String confirmHeader) {
        this.confirmHeader = confirmHeader;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public String getCancelBody() {
        return cancelBody;
    }

    public void setCancelBody(String cancelBody) {
        this.cancelBody = cancelBody;
    }

    public String getCancelHeader() {
        return cancelHeader;
    }

    public void setCancelHeader(String cancelHeader) {
        this.cancelHeader = cancelHeader;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getCallbackHeader() {
        return callbackHeader;
    }

    public void setCallbackHeader(String callbackHeader) {
        this.callbackHeader = callbackHeader;
    }
}
