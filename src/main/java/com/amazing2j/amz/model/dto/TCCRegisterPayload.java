package com.amazing2j.amz.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TCCRegisterPayload {
    @JsonProperty("try_url")
    private String tryUrl;

    @JsonProperty("try_body")
    private String tryBody;

    @JsonProperty("confirm_url")
    private String confirmUrl;

    @JsonProperty("confirm_body")
    private String confirmBody;

    @JsonProperty("cancel_url")
    private String cancelUrl;

    @JsonProperty("cancel_body")
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
