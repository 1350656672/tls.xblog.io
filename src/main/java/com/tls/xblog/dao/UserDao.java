package com.tls.xblog.dao;

import com.tls.xblog.entity.Comment;
import com.tls.xblog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    User selectUser(String uid, String passwd);
}
