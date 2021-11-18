package com.amazing2j.amz.amz_client.controller.activemq;

import com.alibaba.fastjson.JSON;
import com.amazing2j.amz.amz_client.service.activemq.ProducerService;
import com.amazing2j.amz.amz_server.model.dto.activemq.TCCTaskDto;
import com.amazing2j.amz.amz_server.utils.JsonResponse;
import com.amazing2j.amz.amz_server.utils.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/activemq")
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/tx_task/producer")
    public JsonResult sendQueue(@RequestBody TCCTaskDto tccTaskDto) {
        String taskJsonStr = JSON.toJSONString(tccTaskDto);
        producerService.sendTxTask2ActiveMQ(taskJsonStr);
        return JsonResponse.getSuccessResult();
    }
}
