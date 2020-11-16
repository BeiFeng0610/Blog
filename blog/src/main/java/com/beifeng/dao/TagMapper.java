package com.beifeng.dao;

import com.beifeng.domain.Tag;
import com.beifeng.vo.TagVo;

import java.util.List;

public interface TagMapper {

    List<Tag> getAllTag();

    Tag getTagByName(String name);

    void saveTag(Tag tag);

    Tag getTagById(String id);

    Integer updateTag(Tag tag);

    Integer deleteTag(String id);

    List<Tag> getTagsByIds(List<String> tagIds);

    void deleteTagAndblogs(String id);

    List<TagVo> getTagList();
}