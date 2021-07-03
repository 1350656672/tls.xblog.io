package com.tls.xblog.service;

import com.tls.xblog.entity.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {
    String insertBlog(Blog blog);
    String updateBlog(Blog blog);
    int trashBlog(Long id);
    int restoreBlog(Long id);
    int deleteBlog(Long id);

    Blog selectBlogById(Long id);
    List<Blog> selectAllBlog();
    List<Blog> getBlogsByType(Long id);
    List<Blog> getBlogsByTags(List ids);
    Map getRecommendBlogs(int pageNum, int pageSize);
    Map searchBlogs(int pageNum, int pageSize,String searchKey);
    List<Blog> getCompletedBlogs();
    List<Blog> getUncompletedBlogs();
    List<Blog> getTrashBlogs();
}
