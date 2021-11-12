package com.amazing2j.amz.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TCCEntity extends BaseEntity {
    @JsonProperty("url")
    private String url;

    @JsonProperty("branch_type")
    private int branchType;

    @JsonProperty("body_data")
    private String bodyData;

    @JsonProperty("status")
    private int status;

    @JsonProperty("tx_owner")
    private String txOwner;

    @JsonProperty("next_opt")
    private String nextOpt;

    @JsonProperty("finish_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishAt;

    @JsonProperty("rollback_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rollbackAt;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getBranchType() {
        return branchType;
    }

    public void setBranchType(int branchType) {
        this.branchType = branchType;
    }

    public String getBodyData() {
        return bodyData;
    }

    public void setBodyData(String bodyData) {
        this.bodyData = bodyData;
    }

    public int getStatus() {
        return status;
    }

    public String getTxOwner() {
        return txOwner;
    }

    public void setTxOwner(String txOwner) {
        this.txOwner = txOwner;
    }

    public String getNextOpt() {
        return nextOpt;
    }

    public void setNextOpt(String nextOpt) {
        this.nextOpt = nextOpt;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(Date finishAt) {
        this.finishAt = finishAt;
    }

    public Date getRollbackAt() {
        return rollbackAt;
    }

    public void setRollbackAt(Date rollbackAt) {
        this.rollbackAt = rollbackAt;
    }
}
