package com.tls.xblog.service;

import com.tls.xblog.entity.Blog;
import com.tls.xblog.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findCommentsById(Long blogId);
    int addCommentsById(Comment comment);
    int deleteCommentsById(Long id);


}
