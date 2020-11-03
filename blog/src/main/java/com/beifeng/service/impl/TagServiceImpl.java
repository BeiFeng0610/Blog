package com.beifeng.service.impl;

import com.beifeng.dao.TagMapper;
import com.beifeng.domain.Tag;
import com.beifeng.domain.Type;
import com.beifeng.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        tagMapper.saveTag(tag);
    }

    @Transactional
    @Override
    public Tag getTagById(Long id) {
        return tagMapper.getTagById(id);
    }

    @Transactional
    @Override
    public String updateTag(String name,Long id) {
        String msg;
        Integer count = tagMapper.updateTag(name,id);

        if (count == 0 ) {
            msg = "修改失败";
        } else {
            msg = "修改成功";
        }

        return msg;
    }

    @Transactional
    @Override
    public String deleteTag(Long id) {
        Integer count = tagMapper.deleteTag(id);
        String msg;
        if (count == 0 ) {
            msg = "删除失败";
        } else {
            msg = "删除成功";
        }
        return msg;
    }
}
