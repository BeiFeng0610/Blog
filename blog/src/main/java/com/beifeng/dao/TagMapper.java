package com.beifeng.dao;

import com.beifeng.domain.Tag;

import java.util.List;

public interface TagMapper {

    List<Tag> getAllTag();

    Tag getTagByName(String name);

    void saveTag(Tag tag);

    Tag getTagById(Long id);

    Integer updateTag(String name, Long id);

    Integer deleteTag(Long id);
}