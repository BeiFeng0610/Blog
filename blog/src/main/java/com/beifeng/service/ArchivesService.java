package com.beifeng.service;


import com.beifeng.vo.ArchivesVo;

import java.util.List;
import java.util.Map;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/23 23:36
 */
public interface ArchivesService {

    Map<String,List<ArchivesVo>> getArchivesByYear();

}
