package com.beifeng.service.impl;

import com.beifeng.dao.TagMapper;
import com.beifeng.domain.Tag;
import com.beifeng.domain.Type;
import com.beifeng.service.TagService;
import com.beifeng.util.DateTimeUtil;
import com.beifeng.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/3 23:02
 */
@Service
public class TagServiceImpl implements TagService {


    @Autowired
    private TagMapper tagMapper;

    @Transactional
    @Override
    public List<Tag> getAllTag() {

        return tagMapper.getAllTag();
    }

    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Transactional
    @Override
    public void saveTag(Tag tag) {
        tag.setId(UUIDUtil.getUUID());
        tag.setCreateTime(DateTimeUtil.getSysTime());
        tag.setUpdateTime(DateTimeUtil.getSysTime());

        tagMapper.saveTag(tag);
    }

    @Transactional
    @Override
    public Tag getTagById(String id) {
        return tagMapper.getTagById(id);
    }

    @Transactional
    @Override
    public String updateTag(String name,String id) {
        String msg;

        Tag tag = new Tag();
        tag.setId(id);
        tag.setName(name);
        tag.setUpdateTime(DateTimeUtil.getSysTime());
        Integer count = tagMapper.updateTag(tag);

        if (count == 0 ) {
            msg = "修改失败";
        } else {
            msg = "修改成功";
        }

        return msg;
    }

    @Transactional
    @Override
    public String deleteTag(String id) {
        String msg= "删除失败";
        /*
            删除标签，博客关联的标签也会被删除
        */
        tagMapper.deleteTagAndblogs(id);

        Integer count = tagMapper.deleteTag(id);

        if (count == 1 ) {
            msg = "删除成功";
        }

        return msg;
    }

}
