package com.tls.xblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

import java.util.Date;

@Data
public class Blog {
    private long id;
    private long userId;
    private String content;
    private String description;
    private String firstPicture;
    private String flag;
    private String title;
    private Date updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private int views;
    private long typeId;
    private int commentCount;
    private Boolean complete;
    private Boolean trash;

}
