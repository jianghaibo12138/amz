package com.amazing2j.amz.service.impl.TCCService;

import com.amazing2j.amz.constant.enums.ResultEnum;
import com.amazing2j.amz.constant.enums.TCCBranchTypeEnum;
import com.amazing2j.amz.constant.enums.TCCStatusEnum;
import com.amazing2j.amz.exception.SQLException;
import com.amazing2j.amz.model.entity.TCCEntity;
import com.amazing2j.amz.mapper.TCCMapper;
import com.amazing2j.amz.model.dto.TCCRegisterPayload;
import com.amazing2j.amz.utils.TXIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TCCService {
    final TCCMapper tccMapper;
    final TXIdGenerator idGenerator;

    public TCCService(TCCMapper tccMapper, TXIdGenerator idGenerator) {
        this.tccMapper = tccMapper;
        this.idGenerator = idGenerator;
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
        TCCEntity tryEntity = packageTCCEntity(txId, payload.getTryUrl(), TCCBranchTypeEnum.TRY_BRANCH.getType(), payload.getTryBody(), TCCStatusEnum.INITIAL.getStatus());
        affectRows = tccMapper.insertTCC(tryEntity);
        if (affectRows != 1) {
            throw new SQLException(ResultEnum.ERROR.code, "TCC: Insert try branch error");
        }

//        confirm branch
        TCCEntity confirmEntity = packageTCCEntity(txId, payload.getConfirmUrl(), TCCBranchTypeEnum.CONFIRM_BRANCH.getType(), payload.getConfirmBody(), TCCStatusEnum.INITIAL.getStatus());
        affectRows = tccMapper.insertTCC(confirmEntity);
        if (affectRows != 1) {
            throw new SQLException(ResultEnum.ERROR.code, "TCC: Insert confirm branch error");
        }
//
//      cancel branch
        TCCEntity cancelEntity = packageTCCEntity(txId, payload.getCancelUrl(), TCCBranchTypeEnum.CANCEL_BRANCH.getType(), payload.getCancelBody(), TCCStatusEnum.INITIAL.getStatus());
        affectRows = tccMapper.insertTCC(cancelEntity);
        if (affectRows != 1) {
            throw new SQLException(ResultEnum.ERROR.code, "TCC: Insert cancel branch error");
        }



        return txId;
    }

    private TCCEntity packageTCCEntity(long txId, String url, Integer branchType, String bodyData, Integer status) {
        TCCEntity entity = new TCCEntity();
        entity.setTxId(txId);
        entity.setUrl(url);
        entity.setBranchType(branchType);
        entity.setBodyData(bodyData);
        entity.setStatus(status);
        return entity;
    }
}
