package com.amazing2j.amz.amz_server.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TCCMapper {

    long getLatestTCCTxId();
}
