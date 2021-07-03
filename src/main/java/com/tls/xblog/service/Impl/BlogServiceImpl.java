package com.tls.xblog.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tls.xblog.dao.BlogDao;
import com.tls.xblog.dao.TestDao;
import com.tls.xblog.entity.Blog;
import com.tls.xblog.service.BlogService;
import com.tls.xblog.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("BlogService")
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogDao blogDao;

    @Override
    public String insertBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blogDao.insertBlog(blog);
        if (blog.getComplete()){
            return "发布成功";
        }else {
            return "已存至草稿箱！";
        }
    }

    @Override
    public String updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        blogDao.updateBlog(blog);
        if (blog.getComplete()){
            return "重新发布";
        }else {
            return "已更新至草稿箱";
        }
    }

    @Override
    public int trashBlog(Long id) {
        return blogDao.trashBlog(id);
    }

    @Override
    public int restoreBlog(Long id) {
        return blogDao.restoreBlog(id);
    }

    @Override
    public int deleteBlog(Long id) {
        return blogDao.deleteBlog(id);
    }

    @Override
    public Blog selectBlogById(Long id) {
        return blogDao.selectBlogById(id);
    }

    @Override
    public List<Blog> selectAllBlog() {
        return blogDao.selectAllBlog();
    }

    @Override
    public List<Blog> getBlogsByType(Long id) {
        return blogDao.getBlogsByType(id);
    }

    @Override
    public List<Blog> getBlogsByTags(List ids) {
        return blogDao.getBlogsByTags(ids);
    }

    @Override
    public List<Blog> getCompletedBlogs() {
        return blogDao.getCompletedBlogs();
    }

    @Override
    public List<Blog> getUncompletedBlogs() {
        return blogDao.getUncompletedBlogs();
    }

    @Override
    public List<Blog> getTrashBlogs() {
        return blogDao.getTrashBlogs();
    }

    @Override
    public Map getRecommendBlogs(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> recommendBlogs = blogDao.getRecommendBlogs();
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(recommendBlogs);
        Map map = new HashMap();
        map.put("blogs",recommendBlogs);
        map.put("pageInfo",pageInfo);
        return map;
    }

    @Override
    public Map searchBlogs(int pageNum, int pageSize, String searchKey) {
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogs = blogDao.searchBlogs(searchKey);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogs);
        Map map = new HashMap();
        map.put("blogs",blogs);
        map.put("pageInfo",pageInfo);
        return map;
    }


}
