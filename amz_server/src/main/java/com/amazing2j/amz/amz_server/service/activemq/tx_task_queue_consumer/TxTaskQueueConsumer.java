package com.amazing2j.amz.amz_server.service.activemq.tx_task_queue_consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.amazing2j.amz.amz_server.config.ActiveMQConfigure;
import com.amazing2j.amz.amz_server.model.dto.activemq.TCCTaskDto;
import com.amazing2j.amz.amz_server.service.trs_manager.TCCManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class TxTaskQueueConsumer {

    private final static Logger logger = LoggerFactory.getLogger(TxTaskQueueConsumer.class);

    final ActiveMQConfigure activeMQConfigure;
    final TCCManagerService tccManagerService;

    public TxTaskQueueConsumer(ActiveMQConfigure activeMQConfigure, TCCManagerService tccManagerService) {
        this.activeMQConfigure = activeMQConfigure;
        this.tccManagerService = tccManagerService;
    }

    //queue模式的消费者
    @JmsListener(destination = "${spring.activemq.tx-task-queue-name}", containerFactory = "queueListener")
    public void readActiveQueue(String taskJsonStr) {
        JSONObject jsonObject = JSON.parseObject(taskJsonStr);
        TCCTaskDto tccTaskDto = new TCCTaskDto(jsonObject.getLong("task_id"), jsonObject.getLong("tx_id"));
        logger.info("[{}] recv tx task: {}", activeMQConfigure.queue(), taskJsonStr);
        this.tccManagerService.getTCCTx(tccTaskDto.getTxId());
    }
}
