package com.amazing2j.amz.amz_server.mapper;

import com.amazing2j.amz.amz_server.model.entity.TCCEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;


@Mapper
public interface TCCMapper {

    Long getLatestTCCTxId();

    ArrayList<TCCEntity> getTCCEntityForUpdateByTxId(long txId);

    void updateTxBranchStatus(long branchId, Integer branchStatus);

    void setTxExecutor(long txId, Long executor);

    void unsetTxExecutor(long txId);

    void incrTxTriedTimes(long txId);

    void unsetTxTriedTimes(long txId);
}
