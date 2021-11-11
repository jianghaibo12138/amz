package com.amazing2j.amz.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TCCEntity extends BaseEntity {
    @JsonProperty("try_url")
    private String tryUrl;

    @JsonProperty("confirm_url")
    private String confirmUrl;

    @JsonProperty("cancel_url")
    private String cancelUrl;

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
}
