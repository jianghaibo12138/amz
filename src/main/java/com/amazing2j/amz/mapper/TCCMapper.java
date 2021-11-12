package com.amazing2j.amz.mapper;

import com.amazing2j.amz.model.entity.TCCEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TCCMapper {

    int insertTCC(TCCEntity entity);

    List<TCCEntity> selectAllTCC();

    List<TCCEntity> selectTCCByTxId(long txId);

    TCCEntity selectNotFinishedTCCByBranchType(long txId, int branchType);

    TCCEntity selectTCCById(long id);

    int updateTCCStatus(long tccId, int status);
}
