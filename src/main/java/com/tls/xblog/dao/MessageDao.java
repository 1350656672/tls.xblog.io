package com.tls.xblog.dao;

import com.tls.xblog.entity.Comment;
import com.tls.xblog.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageDao {
    List<Message> findMessageHasParent();
    List<Message> findMessageNoParent();
    int addMessage(Message message);
    int deleteMessage(Long id);
}
