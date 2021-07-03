package com.tls.xblog.service.Impl;

import com.tls.xblog.dao.MessageDao;
import com.tls.xblog.entity.Comment;
import com.tls.xblog.entity.Message;
import com.tls.xblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("MessageService")
public class MessageImpl implements MessageService {

    @Autowired
    MessageDao messageDao;
    @Override
    public List<Message> findMessages() {
        List<Message> messagesNoParent = messageDao.findMessageNoParent();
        List<Message> messagesHasParent = messageDao.findMessageHasParent();
        findSonMessages(messagesHasParent,messagesNoParent);
        return messagesNoParent;
    }
    void findSonMessages(List<Message> messagesHasParent,List<Message> messagesNoParent){
        for (Message m1 : messagesNoParent){
            for (Message m2 : messagesHasParent){
                if (m2.getParentId()==m1.getId()){
                    //添加子评论
                    m1.getRelyMessages().add(m2);
                    //递归子的后代评论
                    findGrandMessages(m2,m1.getRelyMessages(),messagesHasParent);
                }
            }
        }
    }
    //递归寻找后代评论
    void findGrandMessages(Message msg,List<Message> relyMessages,List<Message> messagesHasParent){
        for (Message m:messagesHasParent){
            if (m.getParentId()==msg.getId()){
                m.setParentNickname(msg.getNickname());
                relyMessages.add(m);
                findGrandMessages(m,relyMessages,messagesHasParent);
            }
        }
    }

    @Override
    public int addMessage(Message message) {
        message.setCreateTime(new Date());
        if (message.getAvatar()==null){
            message.setAvatar("-1");
        }
        if (message.getParentId()==null){
            message.setParentId(Long.valueOf(-1));
        }
        return messageDao.addMessage(message);
    }

    @Override
    public int deleteMessage(Long id) {
        messageDao.deleteMessage(id);
        return 0;
    }
}
