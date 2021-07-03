package com.tls.xblog.entity;

import lombok.Data;

@Data
public class User {
    private long id;
    private String uid;
    private String username;
    private String passwd;
}
