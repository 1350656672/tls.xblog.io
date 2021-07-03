package com.tls.xblog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.tls.xblog.entity.Type;

import java.util.List;

@Mapper
@Repository
public interface TypeDao {
    /*List<Type> findAllTypesHasBlog();
    Type findTypeByIdHasBlog(Long id);*/
    List<Type> findAllTypes();
    int insertType(Type type);
    int updateType(Type type);
    int deleteType(Long id);
}
