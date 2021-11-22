package com.amazing2j.amz.amz_client.service.tcc;

import com.alibaba.fastjson.JSON;
import com.amazing2j.amz.amz_client.service.activemq.ProducerService;
import com.amazing2j.amz.amz_server.constant.consist.tcc.TCCBranchStatus;
import com.amazing2j.amz.amz_server.constant.consist.tcc.TCCBranchType;
import com.amazing2j.amz.amz_server.constant.enums.ResultEnum;
import com.amazing2j.amz.amz_server.exception.SQLException;
import com.amazing2j.amz.amz_server.model.entity.TCCEntity;
import com.amazing2j.amz.amz_client.mapper.TCCMapper;
import com.amazing2j.amz.amz_client.model.dto.TCCRegisterPayload;
import com.amazing2j.amz.amz_server.model.dto.activemq.TCCTaskDto;
import com.amazing2j.amz.amz_server.utils.TXIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TCCService {
    final TCCMapper tccMapper;

    final ProducerService producerService;

    private final TXIdGenerator idGenerator = new TXIdGenerator();

    public TCCService(TCCMapper tccMapper, ProducerService producerService) {
        this.tccMapper = tccMapper;
        this.producerService = producerService;
    }

    public List<TCCEntity> findAllTCC() {
        return tccMapper.selectAllTCC();
    }

    public TCCEntity findTCCById(long id) {
        return tccMapper.selectTCCById(id);
    }

    private final static Logger logger = LoggerFactory.getLogger(TCCService.class);

    @Transactional
    public long createTCC(TCCRegisterPayload payload) {
        long txId = idGenerator.snowflakeId();
        int affectRows = 0;
//        try branch
        TCCEntity tryEntity = packageTCCEntity(txId, payload.getTryUrl(), payload.getTryMethod(), payload.getTryRest(), TCCBranchType.TRY_BRANCH, payload.getTryBody(), payload.getTryHeader());
        affectRows = tccMapper.insertTCC(tryEntity);
        if (affectRows != 1) {
            throw new SQLException(ResultEnum.ERROR.code, "TCC: Insert try branch error");
        }

//        confirm branch
        TCCEntity confirmEntity = packageTCCEntity(txId, payload.getConfirmUrl(), payload.getConfirmMethod(), payload.getConfirmRest(), TCCBranchType.CONFIRM_BRANCH, payload.getConfirmBody(), payload.getConfirmHeader());
        affectRows = tccMapper.insertTCC(confirmEntity);
        if (affectRows != 1) {
            throw new SQLException(ResultEnum.ERROR.code, "TCC: Insert confirm branch error");
        }
//
//      cancel branch
        TCCEntity cancelEntity = packageTCCEntity(txId, payload.getCancelUrl(), payload.getCancelMethod(), payload.getCancelRest(), TCCBranchType.CANCEL_BRANCH, payload.getCancelBody(), payload.getCancelHeader());
        affectRows = tccMapper.insertTCC(cancelEntity);
        if (affectRows != 1) {
            throw new SQLException(ResultEnum.ERROR.code, "TCC: Insert cancel branch error");
        }

        TCCTaskDto tccTaskDto = new TCCTaskDto(idGenerator.snowflakeId(), txId);
        String taskJsonStr = JSON.toJSONString(tccTaskDto);
        producerService.sendTxTask2ActiveMQ(taskJsonStr);

        return txId;
    }

    private TCCEntity packageTCCEntity(long txId, String url, String method, Integer isRest, Integer branchType, String bodyData, String header) {
        TCCEntity entity = new TCCEntity();
        entity.setTxId(txId);
        entity.setMethod(method);
        entity.setRest(isRest);
        entity.setUrl(url);
        entity.setBranchType(branchType);
        entity.setBodyData(bodyData);
        entity.setHeader(header);
        entity.setExecutor(null);
        entity.setBranchStatus(TCCBranchStatus.TODO);
        entity.setTriedTimes(0);
        return entity;
    }
}
