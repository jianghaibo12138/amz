package com.amazing2j.amz.amz_server.model.dto.activemq;

import com.alibaba.fastjson.annotation.JSONField;

public class TCCTaskDto {

    @JSONField(name = "task_id")
    Long taskId;

    @JSONField(name = "tx_id")
    Long txId;

    public TCCTaskDto() {
    }

    public TCCTaskDto(Long taskId, Long txId) {
        this.taskId = taskId;
        this.txId = txId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getTxId() {
        return txId;
    }

    public void setTxId(Long txId) {
        this.txId = txId;
    }
}
