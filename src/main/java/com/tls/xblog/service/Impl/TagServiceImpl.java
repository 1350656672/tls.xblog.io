package com.tls.xblog.service.Impl;

import com.tls.xblog.dao.TagDao;
import com.tls.xblog.entity.Tag;
import com.tls.xblog.entity.Type;
import com.tls.xblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("TagService")
public class TagServiceImpl implements TagService {
    @Autowired
    TagDao tagDao;

    @Override
    public List<Tag> findAllTags() {
        return tagDao.findAllTags();
    }

    @Override
    public String insertTag(Tag tag) {
        tagDao.insertTag(tag);
        return "标签创建成功";
    }

    @Override
    public String updateTag(Tag tag) {
        tagDao.updateTag(tag);
        return  "标签更新成功";
    }

    @Override
    public String deleteTag(Long id) {
        tagDao.deleteTag(id);
        return  "标签删除成功";
    }
}
