package com.beifeng.service.impl;

import com.beifeng.dao.ArchivesMapper;
import com.beifeng.service.ArchivesService;
import com.beifeng.vo.ArchivesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/23 23:48
 */
@Service
public class ArchivesServiceImpl implements ArchivesService {

    @Autowired
    private ArchivesMapper archivesMapper;

    @Transactional
    @Override
    public Map<String,List<ArchivesVo>> getArchivesByYear() {

        List<String> yearList = archivesMapper.getYear();

        Map<String,List<ArchivesVo>> archivesVoMap = new TreeMap<>(Comparator.reverseOrder());
        for (String year : yearList){
            List<ArchivesVo> archivesVoList = archivesMapper.getArchivesListByYear(year);
            archivesVoMap.put(year, archivesVoList);
        }

        return archivesVoMap;
    }
}
