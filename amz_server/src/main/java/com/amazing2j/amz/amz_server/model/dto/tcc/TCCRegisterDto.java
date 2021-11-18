package com.amazing2j.amz.amz_server.model.dto.tcc;

import com.alibaba.fastjson.annotation.JSONField;

public class TCCRegisterDto {
    @JSONField(name = "try_url")
    private String tryUrl;

    @JSONField(name = "try_body")
    private String tryBody;

    @JSONField(name = "confirm_url")
    private String confirmUrl;

    @JSONField(name = "confirm_body")
    private String confirmBody;

    @JSONField(name = "cancel_url")
    private String cancelUrl;

    @JSONField(name = "cancel_body")
    private String cancelBody;

    public String getTryUrl() {
        return tryUrl;
    }

    public void setTryUrl(String tryUrl) {
        this.tryUrl = tryUrl;
    }

    public String getConfirmUrl() {
        return confirmUrl;
    }

    public void setConfirmUrl(String confirmUrl) {
        this.confirmUrl = confirmUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public String getTryBody() {
        return tryBody;
    }

    public void setTryBody(String tryBody) {
        this.tryBody = tryBody;
    }

    public String getConfirmBody() {
        return confirmBody;
    }

    public void setConfirmBody(String confirmBody) {
        this.confirmBody = confirmBody;
    }

    public String getCancelBody() {
        return cancelBody;
    }

    public void setCancelBody(String cancelBody) {
        this.cancelBody = cancelBody;
    }
}
