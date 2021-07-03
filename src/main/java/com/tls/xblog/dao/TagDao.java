package com.tls.xblog.dao;

import com.tls.xblog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagDao {
    List<Tag> findAllTags();
    int insertTag(Tag tag);
    int updateTag(Tag tag);
    int deleteTag(Long id);
}
