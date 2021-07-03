package com.tls.xblog.service.Impl;

import com.tls.xblog.dao.CommentDao;
import com.tls.xblog.dao.UserDao;
import com.tls.xblog.entity.Comment;
import com.tls.xblog.entity.User;
import com.tls.xblog.service.CommentService;
import com.tls.xblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User login(String uid, String passwd) {
        return userDao.selectUser(uid,passwd);
    }
}
