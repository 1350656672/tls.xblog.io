package com.tls.xblog.service;

import com.tls.xblog.entity.Type;

import java.util.List;

public interface TypeService {
    /*List<Type> findAllTypesHasBlog();*/
    /*Type findTypeByIdHasBlog(Long id);*/
    List<Type> findAllTypes();
    String insertType(Type type);
    String updateType(Type type);
    String deleteType(Long id);
}
