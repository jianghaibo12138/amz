package com.amazing2j.amz.amz_server.model.dto.tcc;

import com.alibaba.fastjson.annotation.JSONField;

public class TCCExecResultDto {

    @JSONField(name = "branch_type")
    Integer branchType;

    @JSONField(name = "exec_status")
    Integer execStatus;

    @JSONField(name = "exec_result")
    String execResult;

    public Integer getBranchType() {
        return branchType;
    }

    public void setBranchType(Integer branchType) {
        this.branchType = branchType;
    }

    public Integer getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(Integer execStatus) {
        this.execStatus = execStatus;
    }

    public String getExecResult() {
        return execResult;
    }

    public void setExecResult(String execResult) {
        this.execResult = execResult;
    }
}
