package com.tls.xblog.service;

import com.tls.xblog.entity.Comment;
import com.tls.xblog.entity.Message;

import java.util.List;

public interface MessageService {

    List<Message> findMessages();
    int addMessage(Message message);
    int deleteMessage(Long id);


}
