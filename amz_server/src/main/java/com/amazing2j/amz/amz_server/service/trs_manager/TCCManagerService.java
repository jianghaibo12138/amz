package com.amazing2j.amz.amz_server.service.trs_manager;

import com.amazing2j.amz.amz_server.mapper.TCCMapper;
import com.amazing2j.amz.amz_server.model.entity.TCCEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

@Service
public class TCCManagerService {
    private final static Logger logger = LoggerFactory.getLogger(TCCManagerService.class);

    private final ExecutorService executorService = TCCThreadPoolExecutor.tccExecutorPool(10, 50);

    final TCCMapper tccMapper;

    public TCCManagerService(TCCMapper tccMapper) {
        this.tccMapper = tccMapper;
    }

    public void startTCCTx(long txId) {
        logger.debug("[TCCManagerService startTCCTx] committed TCC execute task, tx_id: {}", txId);
        executorService.submit(new TCCExecuteTask(txId));
    }
}
