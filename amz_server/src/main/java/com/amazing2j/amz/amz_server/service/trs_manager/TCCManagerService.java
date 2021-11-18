package com.amazing2j.amz.amz_server.service.trs_manager;

import com.amazing2j.amz.amz_server.mapper.TCCMapper;
import com.amazing2j.amz.amz_server.model.entity.TCCEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TCCManagerService {
    private final static Logger logger = LoggerFactory.getLogger(TCCManagerService.class);

    final TCCMapper tccMapper;

    public TCCManagerService(TCCMapper tccMapper) {
        this.tccMapper = tccMapper;
    }

    public void getTCCTx(long txId) {
        ArrayList<TCCEntity> entities = tccMapper.getTCCEntityByTxId(txId);
        logger.debug("[TCCService getTCCTx] latest TCC tx_id: {}", entities);
    }
}
