package com.tls.xblog.service.Impl;

import com.tls.xblog.dao.TestDao;
import com.tls.xblog.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TestService")
public class TestServiceImpl implements TestService {
    @Autowired
    TestDao testDao;
    @Override
    public int selectCount() {
        return testDao.selectCount();
    }
}
