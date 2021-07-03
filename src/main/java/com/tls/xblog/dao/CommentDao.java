package com.tls.xblog.dao;

import com.tls.xblog.entity.Blog;
import com.tls.xblog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") long blogId);
    List<Comment> findByBlogIdAndParentId(@Param("blogId")long blogId,@Param("parentId")long parentId);
    int addCommentsById(Comment comment);
    int deleteCommentsById(Long id);
}
