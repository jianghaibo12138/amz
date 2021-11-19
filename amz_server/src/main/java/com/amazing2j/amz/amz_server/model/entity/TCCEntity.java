package com.amazing2j.amz.amz_server.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TCCEntity extends BaseEntity {
    @JSONField(name = "url")
    private String url;

    @JSONField(name = "branch_type")
    private int branchType;

    @JSONField(name = "body_data")
    private String bodyData;

    @JSONField(name = "executor")
    private String executor;

    @JSONField(name = "next_opt")
    private String nextOpt;

    @JSONField(name = "branch_status")
    private int branchStatus;

    @JSONField(name = "finish_at", format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishAt;

    @JSONField(name = "rollback_at", format = "yyyy-MM-dd HH:mm:ss")
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

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getNextOpt() {
        return nextOpt;
    }

    public void setNextOpt(String nextOpt) {
        this.nextOpt = nextOpt;
    }

    public int getBranchStatus() {
        return branchStatus;
    }

    public void setBranchStatus(int branchStatus) {
        this.branchStatus = branchStatus;
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
