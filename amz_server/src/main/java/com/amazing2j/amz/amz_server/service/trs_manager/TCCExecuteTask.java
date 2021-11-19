package com.amazing2j.amz.amz_server.service.trs_manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.amazing2j.amz.amz_server.constant.consist.tcc.TCCBranchStatus;
import com.amazing2j.amz.amz_server.constant.consist.tcc.TCCBranchType;
import com.amazing2j.amz.amz_server.constant.consist.tcc.TCCMethod;
import com.amazing2j.amz.amz_server.constant.enums.ResultEnum;
import com.amazing2j.amz.amz_server.exception.HTTPException;
import com.amazing2j.amz.amz_server.exception.TXException;
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
import java.util.Map;

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

        if (entities.size() != 3) {
            logger.debug("[TCCExecuteTask run] the size of target TCC branches invalid, Size: {}", entities.size());
            dataSourceTransactionManager.commit(transactionStatus);
            return;
        }

        Map<Integer, TCCEntity> tccMap = new HashMap<>();
        for (TCCEntity entity : entities) {
            logger.debug("[TCCExecuteTask run] set executor: {}", executor);
            tccMapper.updateTxBranchStatus(entity.getId(), TCCBranchStatus.IDLE);
            logger.debug("[TCCExecuteTask run] update branch: {}, set status: {}", entity.getId(), TCCBranchStatus.IDLE);
            tccMap.put(entity.getBranchType(), entity);
        }
        tccMapper.setTxExecutor(this.txId, executor);
        try {
            //        执行TRY分支
            try {
                String tryResponseStr = this.doTx(tccMap.get(TCCBranchType.TRY_BRANCH).getMethod(), tccMap.get(TCCBranchType.CONFIRM_BRANCH).getRest(), tccMap.get(TCCBranchType.TRY_BRANCH).getUrl(), tccMap.get(TCCBranchType.TRY_BRANCH).getHeader(), tccMap.get(TCCBranchType.TRY_BRANCH).getBodyData());
                logger.debug("[TCCExecuteTask run] TRY branch: {} DONE, branch type: {}, branch status: {}, response: {}", tccMap.get(TCCBranchType.TRY_BRANCH).getId(), tccMap.get(TCCBranchType.TRY_BRANCH).getBranchType(), tccMap.get(TCCBranchType.TRY_BRANCH).getBranchStatus(), tryResponseStr);

                JSONObject tryJsonObject = JSON.parseObject(tryResponseStr);
                Integer tryCode = tryJsonObject.getInteger("code");
                if (tryCode != 200) {
//            TRY分支执行失败, 将整个TX设置为失败
                    logger.error("[TCCExecuteTask run] TRY branch: {} FAILED, branch type: {}, branch status: {}, response: {}", tccMap.get(TCCBranchType.TRY_BRANCH).getId(), tccMap.get(TCCBranchType.TRY_BRANCH).getBranchType(), tccMap.get(TCCBranchType.TRY_BRANCH).getBranchStatus(), tryResponseStr);
                    throw new TXException(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage());
                }
            } catch (Exception e) {
                logger.error("[TCCExecuteTask run] exec tx tx_id: {}, TRY branch: {} FAILED, exception: {}, rollback", this.txId, tccMap.get(TCCBranchType.TRY_BRANCH).getId(), e.getMessage());
                tccMapper.updateTxBranchStatus(tccMap.get(TCCBranchType.TRY_BRANCH).getId(), TCCBranchStatus.FAILED);
                tccMapper.updateTxBranchStatus(tccMap.get(TCCBranchType.CONFIRM_BRANCH).getId(), TCCBranchStatus.FAILED);
                tccMapper.updateTxBranchStatus(tccMap.get(TCCBranchType.CANCEL_BRANCH).getId(), TCCBranchStatus.FAILED);

                tccMapper.unsetTxExecutor(this.txId);
                tccMapper.incrTxTriedTimes(this.txId);
                dataSourceTransactionManager.commit(transactionStatus);
                return;
            }
            tccMapper.unsetTxTriedTimes(this.txId);
            tccMapper.updateTxBranchStatus(tccMap.get(TCCBranchType.TRY_BRANCH).getId(), TCCBranchStatus.SUCCESS);

//        执行CONFIRM分支
            try {
                String confirmResponseStr = this.doTx(tccMap.get(TCCBranchType.CONFIRM_BRANCH).getMethod(), tccMap.get(TCCBranchType.CONFIRM_BRANCH).getRest(), tccMap.get(TCCBranchType.CONFIRM_BRANCH).getUrl(), tccMap.get(TCCBranchType.CONFIRM_BRANCH).getHeader(), tccMap.get(TCCBranchType.CONFIRM_BRANCH).getBodyData());
                logger.debug("[TCCExecuteTask run] CONFIRM branch: {} DONE, branch type: {}, branch status: {}, response: {}", tccMap.get(TCCBranchType.CONFIRM_BRANCH).getId(), tccMap.get(TCCBranchType.CONFIRM_BRANCH).getBranchType(), tccMap.get(TCCBranchType.CONFIRM_BRANCH).getBranchStatus(), confirmResponseStr);

                JSONObject confirmJsonObject = JSON.parseObject(confirmResponseStr);
                Integer confirmCode = confirmJsonObject.getInteger("code");
                if (confirmCode == 200) {
                    logger.debug("[TCCExecuteTask run] CONFIRM branch: {} SUCCESS, branch type: {}, branch status: {}, response: {}", tccMap.get(TCCBranchType.CONFIRM_BRANCH).getId(), tccMap.get(TCCBranchType.CONFIRM_BRANCH).getBranchType(), tccMap.get(TCCBranchType.CONFIRM_BRANCH).getBranchStatus(), confirmResponseStr);
                    tccMapper.updateTxBranchStatus(tccMap.get(TCCBranchType.CONFIRM_BRANCH).getId(), TCCBranchStatus.SUCCESS);
                    tccMapper.unsetTxTriedTimes(this.txId);
                    tccMapper.unsetTxExecutor(this.txId);
                    dataSourceTransactionManager.commit(transactionStatus);
                    return;
                }
            } catch (Exception e) {
                logger.error("[TCCExecuteTask run] exec tx tx_id: {}, CONFIRM branch: {} FAILED, exception: {}", this.txId, tccMap.get(TCCBranchType.CONFIRM_BRANCH).getId(), e.getMessage());
            }
            tccMapper.incrTxTriedTimes(this.txId);
            tccMapper.updateTxBranchStatus(tccMap.get(TCCBranchType.CONFIRM_BRANCH).getId(), TCCBranchStatus.FAILED);

//        CONFIRM分支执行失败
//        执行CANCEL分支
            try {
                String cancelResponseStr = this.doTx(tccMap.get(TCCBranchType.CANCEL_BRANCH).getMethod(), tccMap.get(TCCBranchType.CANCEL_BRANCH).getRest(), tccMap.get(TCCBranchType.CANCEL_BRANCH).getUrl(), tccMap.get(TCCBranchType.CANCEL_BRANCH).getHeader(), tccMap.get(TCCBranchType.CANCEL_BRANCH).getBodyData());
                logger.debug("[TCCExecuteTask run] CANCEL branch: {} DONE, branch type: {}, branch status: {}, response: {}", tccMap.get(TCCBranchType.CANCEL_BRANCH).getId(), tccMap.get(TCCBranchType.CANCEL_BRANCH).getBranchType(), tccMap.get(TCCBranchType.CANCEL_BRANCH).getBranchStatus(), cancelResponseStr);

                JSONObject cancelJsonObject = JSON.parseObject(cancelResponseStr);
                Integer cancelCode = cancelJsonObject.getInteger("code");
                if (cancelCode == 200) {
                    logger.debug("[TCCExecuteTask run] CANCEL branch: {} SUCCESS, branch type: {}, branch status: {}, response: {}", tccMap.get(TCCBranchType.CANCEL_BRANCH).getId(), tccMap.get(TCCBranchType.CANCEL_BRANCH).getBranchType(), tccMap.get(TCCBranchType.CANCEL_BRANCH).getBranchStatus(), cancelResponseStr);
                    tccMapper.updateTxBranchStatus(tccMap.get(TCCBranchType.CANCEL_BRANCH).getId(), TCCBranchStatus.SUCCESS);
                    tccMapper.unsetTxTriedTimes(this.txId);
                } else {
                    logger.error("[TCCExecuteTask run] CANCEL branch: {} FAILED, branch type: {}, branch status: {}, response: {}", tccMap.get(TCCBranchType.CANCEL_BRANCH).getId(), tccMap.get(TCCBranchType.CANCEL_BRANCH).getBranchType(), tccMap.get(TCCBranchType.CANCEL_BRANCH).getBranchStatus(), cancelResponseStr);
                    tccMapper.updateTxBranchStatus(tccMap.get(TCCBranchType.CANCEL_BRANCH).getId(), TCCBranchStatus.FAILED);
                    tccMapper.incrTxTriedTimes(this.txId);
                }
            } catch (Exception e) {
                logger.error("[TCCExecuteTask run] exec tx tx_id: {}, CANCEL branch: {} FAILED, exception: {}", this.txId, tccMap.get(TCCBranchType.CANCEL_BRANCH).getId(), e.getMessage());
                tccMapper.updateTxBranchStatus(tccMap.get(TCCBranchType.CANCEL_BRANCH).getId(), TCCBranchStatus.FAILED);
                tccMapper.incrTxTriedTimes(this.txId);
            } finally {
                logger.debug("[TCCExecuteTask run] commit transaction, tx_id: {}", this.txId);
                tccMapper.unsetTxExecutor(this.txId);
                dataSourceTransactionManager.commit(transactionStatus);
            }
        } catch (Exception e) {
            logger.debug("[TCCExecuteTask run] commit transaction FAILED, tx_id: {}, ROLLBACK", this.txId);
            dataSourceTransactionManager.rollback(transactionStatus);
        }

    }

    private String doTx(String method, Integer rest, String url, String headerStr, String bodyData) {
        Map<String, Object> header = JSON.parseObject(headerStr);
        Object body = JSON.parseObject(bodyData);
        String resultStr = null;
        try {
            switch (method) {
                case TCCMethod.METHOD_GET:
                    if (rest == 1) {
                        resultStr = okHttp.sendGetRequestRest(url, header, null);
                    } else {
                        resultStr = okHttp.sendGetRequest(url, header, null);
                    }
                case TCCMethod.METHOD_POST:
                    if (rest == 1) {
                        resultStr = okHttp.sendPostRequestRestByJson(url, header, null, body);
                    } else {
                        resultStr = okHttp.sendPostRequestByJson(url, header, null, body);
                    }
                case TCCMethod.METHOD_PUT:
                    if (rest == 1) {
                        resultStr = okHttp.sendPutRequestRestByJson(url, header, null, body);
                    } else {
                        resultStr = okHttp.sendPutRequestByJson(url, header, null, body);
                    }
                case TCCMethod.METHOD_DELETE:
                    if (rest == 1) {
                        resultStr = okHttp.sendDeleteRequestRest(url, header, null);
                    } else {
                        resultStr = okHttp.sendDeleteRequest(url, header, null);
                    }
                default:
                    throw new HTTPException(405, "Method Not Allow");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultStr;
    }
}
