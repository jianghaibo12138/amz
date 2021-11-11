package com.amazing2j.amz.service.TCCService;

import com.amazing2j.amz.entity.TCCEntity;
import com.amazing2j.amz.dao.TCCDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TCCService {
    final TCCDao tccDao;

    public TCCService(TCCDao tccDao) {
        this.tccDao = tccDao;
    }

    public List<TCCEntity> findAllTCC() {
        return tccDao.selectAllTCC();
    }

    public TCCEntity findTCCById(long id) {
        return tccDao.selectTCCById(id);
    }

    public Integer createTCC(TCCEntity entity) {
        return tccDao.insertTCC(entity);
    }

}
