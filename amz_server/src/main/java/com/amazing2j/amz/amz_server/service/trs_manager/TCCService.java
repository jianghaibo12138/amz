package com.amazing2j.amz.amz_server.service.trs_manager;

import com.amazing2j.amz.amz_server.mapper.TCCMapper;
import com.amazing2j.amz.amz_server.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCCService {
    private final static Logger logger = LoggerFactory.getLogger(TCCService.class);

    private TCCMapper tccMapper;

    public TCCService() {
        this.initMapper();
    }

    private void initMapper() {
        this.tccMapper = (TCCMapper) SpringContextUtil.getBean(TCCMapper.class);
    }

    public void getTCCTx() {
        long latestTxId = tccMapper.getLatestTCCTxId();
        logger.debug("[TCCService getTCCTx] latest TCC tx_id: {}", latestTxId);
    }

}
