package com.tls.xblog.service.Impl;

import com.tls.xblog.dao.TypeDao;
import com.tls.xblog.entity.Type;
import com.tls.xblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("TypeService")
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeDao typeDao;


   /* @Override
    public List<Type> findAllTypesHasBlog() {
        return typeDao.findAllTypesHasBlog();
    }*/

    /*@Override
    public Type findTypeByIdHasBlog(Long id) {
        return typeDao.findTypeByIdHasBlog(id);
    }*/

    @Override
    public List<Type> findAllTypes() {
        return typeDao.findAllTypes();
    }

    @Override
    public String insertType(Type type) {
        typeDao.insertType(type);
        return "已添加";
    }

    @Override
    public String updateType(Type type) {
        typeDao.updateType(type);
        return "修改成功";
    }

    @Override
    public String deleteType(Long id) {
        typeDao.deleteType(id);
        return "已删除";
    }
}
