package com.amazing2j.amz.dao;

import com.amazing2j.amz.entity.TCCEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TCCDao {

    List<TCCEntity> selectAllTCC();

    TCCEntity selectTCCById(long id);

    Integer insertTCC(TCCEntity entity);
}
