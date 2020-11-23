package com.beifeng.dao;

import com.beifeng.vo.ArchivesVo;

import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/23 23:48
 */
public interface ArchivesMapper {
    List<String> getYear();

    List<ArchivesVo> getArchivesListByYear(String year);
}
