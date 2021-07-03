package com.tls.xblog.dao;

import com.tls.xblog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogDao {
    int insertBlog(Blog blog);
    int updateBlog(Blog blog);
    int trashBlog(Long id);
    int restoreBlog(Long id);
    int deleteBlog(Long id);

    List<Blog> selectAllBlog();
    Blog selectBlogById(Long id);
    List<Blog> getBlogsByType(Long id);
    List<Blog> getBlogsByTags(List ids);
    //分页查询，sql语句直接查询全部，在service层分页即可
    List<Blog> getRecommendBlogs();
    List<Blog> searchBlogs(String searchKey);
    List<Blog> getUncompletedBlogs();
    List<Blog> getCompletedBlogs();
    List<Blog> getTrashBlogs();
}
