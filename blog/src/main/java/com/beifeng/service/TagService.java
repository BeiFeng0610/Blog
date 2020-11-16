package com.beifeng.service;

import com.beifeng.domain.Tag;
import com.beifeng.vo.TagVo;

import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/3 23:01
 */
public interface TagService {

    void saveTag(Tag tag);

    String deleteTag(String id);

    String updateTag(String name, String id);

    Tag getTagById(String id);

    Tag getTagByName(String name);

    List<Tag> getAllTag();

    List<TagVo> getTagsVo();
}
