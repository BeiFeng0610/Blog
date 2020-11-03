package com.beifeng.service;

import com.beifeng.domain.Tag;

import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/3 23:01
 */
public interface TagService {

    List<Tag> getAllTag();

    Tag getTagByName(String name);

    void saveTag(Tag tag);

    Tag getTagById(Long id);

    String updateTag(String name, Long id);

    String deleteTag(Long id);
}
