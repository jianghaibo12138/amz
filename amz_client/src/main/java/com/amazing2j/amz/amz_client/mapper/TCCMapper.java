package com.amazing2j.amz.amz_client.mapper;

import com.amazing2j.amz.amz_server.model.entity.TCCEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TCCMapper {

    List<TCCEntity> selectAllTCC();

    List<TCCEntity> selectTCCByTxId(long txId);

    TCCEntity selectTCCById(long id);

    int insertTCC(TCCEntity entity);

    int updateTCCStatus(long tccId, int status);
}
