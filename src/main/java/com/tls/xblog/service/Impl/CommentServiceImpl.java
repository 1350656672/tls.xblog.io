package com.tls.xblog.service.Impl;

import com.tls.xblog.dao.BlogDao;
import com.tls.xblog.dao.CommentDao;
import com.tls.xblog.entity.Blog;
import com.tls.xblog.entity.Comment;
import com.tls.xblog.service.BlogService;
import com.tls.xblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("CommentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;
    List<Comment> tempInnerComments = new ArrayList<>();

    @Override
    //组合所有评论，一级，二级，三级...
    public List<Comment> findCommentsById(Long blogId) {
        List<Comment> comments = commentDao.findByBlogIdParentIdNull(blogId);
        if (comments.size()!=0){
            for (Comment comment:comments) {
                //获取二级评论
                //System.out.println(comment.getId());
                List<Comment> childComments = commentDao.findByBlogIdAndParentId(blogId, comment.getId());
                //System.out.println(childComments.toString());
                //把二级评论及其下级评论组合起来，作为内层评论
                combineInnerComments(blogId,childComments);
                comment.setRelyComments(tempInnerComments);
                //每个内部评论组合完成后，清空临时工具列表
                /**错误示范：此处不能用clear清空，setRelyComments引用的是相同的对象。
                 * 清空tempInnerComments，也会清空comment中的RelyComments
                 * tempInnerComments.clear();
                 * **/
                //用new让tempInnerComments新建一个对象
                tempInnerComments=new ArrayList<>();
                //System.out.println("检查内层评论是否设置成功："+comment.getRelyComments());
            }
        }
        return comments;
    }

    @Override
    public int addCommentsById(Comment comment) {
        comment.setCreateTime(new Date());
        if (comment.getParentCommentId()==null){
            comment.setParentCommentId((long)-1);
        }
        System.out.println(comment.getAvatar());
        if (comment.getAvatar()==null){
            comment.setAvatar("-1");
        }
        System.out.println(comment.getAvatar());
        commentDao.addCommentsById(comment);
        return 0;
    }

    @Override
    public int deleteCommentsById(Long id) {
        commentDao.deleteCommentsById(id);
        return 0;
    }

    //组合内层评论，二级和多级评论，二级评论不需要父评论昵称@xx
    void combineInnerComments(long blogId, List<Comment> childComments){
        if (childComments.size()!=0){
            for (Comment childComment:childComments) {
                //把二级评论添加进内层评论
                tempInnerComments.add(childComment);
                //递归把多级评论（大于二级）添加进内层评论
                recursively(blogId,childComment.getId(),childComment.getNickname());
            }
        }
     }
    //递归寻找多级评论（回复二级评论的三级评论，回复三级评论的四级评论...），多级评论需要父评论昵称@xx
    void recursively(long blogId,long commentId,String commentNickname){
        List<Comment> moreComments = commentDao.findByBlogIdAndParentId(blogId, commentId);
        //System.out.println(moreComments);
        if(moreComments.size()!=0){
            for (Comment moreComment:moreComments) {
                moreComment.setParentNickname(commentNickname);
                //把多级评论添加进来
                tempInnerComments.add(moreComment);
                //递归该多级评论
                recursively(blogId,moreComment.getId(),moreComment.getNickname());
            }
        }
    }

}
