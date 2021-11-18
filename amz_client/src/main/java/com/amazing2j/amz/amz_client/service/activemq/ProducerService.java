package com.amazing2j.amz.amz_client.service.activemq;

import com.amazing2j.amz.amz_server.model.dto.activemq.TCCTaskDto;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.Queue;

@Service
public class ProducerService {

    private final JmsMessagingTemplate jmsMessagingTemplate;

    private final Queue queue;

    public ProducerService(JmsMessagingTemplate jmsMessagingTemplate, Queue queue) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.queue = queue;
    }

    // 发送消息，destination是发送到的队列，message是待发送的消息
    private void sendMessage(Destination destination, final String taskJsonStr) {
        jmsMessagingTemplate.convertAndSend(destination, taskJsonStr);
    }

    public void sendTxTask2ActiveMQ(String taskJsonStr) {
        this.sendMessage(this.queue, taskJsonStr);
    }

}
