package com.amazing2j.amz.amz_server.service.trs_manager;

import com.amazing2j.amz.amz_server.constant.consist.tcc.TCCBranchStatus;
import com.amazing2j.amz.amz_server.mapper.TCCMapper;
import com.amazing2j.amz.amz_server.model.entity.TCCEntity;
import com.amazing2j.amz.amz_server.utils.SpringContextUtil;
import com.amazing2j.amz.amz_server.utils.okhttp.OkHttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TCCExecuteTask implements Runnable {
    private final static Logger logger = LoggerFactory.getLogger(TCCExecuteTask.class);

    TCCMapper tccMapper = SpringContextUtil.getBean(TCCMapper.class);

    OkHttpUtil okHttp = SpringContextUtil.getBean(OkHttpUtil.class);

    DataSourceTransactionManager dataSourceTransactionManager = SpringContextUtil.getBean(DataSourceTransactionManager.class);
    TransactionDefinition transactionDefinition = SpringContextUtil.getBean(TransactionDefinition.class);

    private final Long txId;

    public TCCExecuteTask(Long txId) {
        this.txId = txId;
    }

    @Override
    public void run() {
        long executor = Thread.currentThread().getId();
        //开启事务
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        ArrayList<TCCEntity> entities = tccMapper.getTCCEntityForUpdateByTxId(this.txId);
        logger.debug("[TCCExecuteTask run] target TCC branches: {}", entities);
        try {
            for (TCCEntity entity : entities) {
                tccMapper.updateTxExecutor(entity.getId(), executor);
                logger.debug("[TCCExecuteTask run] set executor: {}", executor);
                tccMapper.updateTxBranchStatus(entity.getId(), TCCBranchStatus.IDLE);
                logger.debug("[TCCExecuteTask run] update branch: {}, set status: {}", entity.getId(), TCCBranchStatus.IDLE);
                this.doTx(entity);
                logger.debug("[TCCExecuteTask run] done branch: {}, branch status: {}", entity.getId(), entity.getBranchStatus());

            }
            dataSourceTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            logger.error("[TCCExecuteTask run] exec tx failed, tx_id: {}, exception: {}, rollback", this.txId, e.getMessage());
            dataSourceTransactionManager.rollback(transactionStatus);
        }

    }

    private boolean doTx(TCCEntity tccEntity) {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJDb21wYW55Q29kZSI6IjE0MDEiLCJFeHAiOiIyMDIxLTExLTA5VDA3OjA3OjI2LjExMjQyNTA4OVoiLCJOZXciOmZhbHNlLCJTaWduZWRUaW1lIjoiMjAyMS0xMS0wMlQwNzowNzoyNi4xMTI0MjQ3MzRaIiwiVXNlck5vIjoiMTM4MTg2NDQ3NTAifQ.BxoSfpEnLNb9YM-_RVK18rNkDmqfGeuC-rUW71SvII8");
        try {
            String responseStr = okHttp.sendGetRequest(tccEntity.getUrl(), headers, null);
            logger.error("[TCCExecuteTask doTx] do tx: {} branch: {} success", tccEntity.getTxId(), tccEntity.getId());
            tccMapper.updateTxBranchStatus(tccEntity.getId(), TCCBranchStatus.SUCCESS);
            return true;
        } catch (IOException e) {
            logger.error("[TCCExecuteTask doTx] do tx: {} branch: {} got exception: {}", tccEntity.getTxId(), tccEntity.getId(), e);
            tccMapper.updateTxBranchStatus(tccEntity.getId(), TCCBranchStatus.FAILED);
        }
        return false;
    }
}
