package com.tls.xblog.entity;

import lombok.Data;

@Data
public class Tag {
    private Long id;
    private String name;
    private int count;
}
