package com.tls.xblog.service;

import com.tls.xblog.entity.Tag;
import com.tls.xblog.entity.Type;


import java.util.List;

public interface TagService {

    List<Tag> findAllTags();
    String insertTag(Tag tag);
    String updateTag(Tag tag);
    String deleteTag(Long id);
}
