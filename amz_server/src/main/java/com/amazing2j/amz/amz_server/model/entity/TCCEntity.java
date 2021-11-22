package com.amazing2j.amz.amz_server.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TCCEntity extends BaseEntity {

    @JSONField(name = "method")
    private String method;

    @JSONField(name = "rest")
    private Integer rest;

    @JSONField(name = "url")
    private String url;

    @JSONField(name = "branch_type")
    private int branchType;

    @JSONField(name = "body_data")
    private String bodyData;

    @JSONField(name = "header")
    private String header;

    @JSONField(name = "executor")
    private Integer executor;

    @JSONField(name = "branch_status")
    private int branchStatus;

    @JSONField(name = "tried_times")
    private int triedTimes;

    @JSONField(name = "finish_at", format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishAt;

    @JSONField(name = "rollback_at", format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rollbackAt;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getRest() {
        return rest;
    }

    public void setRest(Integer rest) {
        this.rest = rest;
    }

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

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Integer getExecutor() {
        return executor;
    }

    public void setExecutor(Integer executor) {
        this.executor = executor;
    }

    public int getBranchStatus() {
        return branchStatus;
    }

    public void setBranchStatus(int branchStatus) {
        this.branchStatus = branchStatus;
    }

    public int getTriedTimes() {
        return triedTimes;
    }

    public void setTriedTimes(int triedTimes) {
        this.triedTimes = triedTimes;
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
