package com.tls.xblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Message {
    /**数据库字段
     * 建议使用包装类，前端部分参数为null，无法被基本类型接收，
    如long无法接收null（尽管spring接收前端参数并封装到Comment对象的时候，会把null以0映射到long）**/
    private long id;
    private String nickname;
    private String content;
    private String avatar;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Long parentId;
    private boolean adminComment;

    //保存父评论信息
    private String parentNickname;

    //保存子评论(包括二级三级四级...)
    private List<Message> relyMessages = new ArrayList<>();

    //邮箱，被回复时，发送邮件提醒
    private String email;
}
