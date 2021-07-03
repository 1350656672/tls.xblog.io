package com.tls.xblog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface TestDao {
    int selectCount();
}
