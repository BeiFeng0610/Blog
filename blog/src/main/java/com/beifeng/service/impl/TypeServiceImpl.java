package com.beifeng.service.impl;

import com.beifeng.dao.TypeMapper;
import com.beifeng.domain.Type;
import com.beifeng.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/2 15:45
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Transactional
    @Override
    public Integer saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeMapper.getTypeById(id);
    }

    @Transactional
    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }

    @Transactional
    @Override
    public String updateType(String name,Long id) {
        String msg;
        Integer count = typeMapper.updateType(name,id);

        if (count == 0 ) {
            msg = "修改失败";
        } else {
            msg = "修改成功";
        }

        return msg;
    }

    @Transactional
    @Override
    public String deleteType(Long id) {
        Integer count = typeMapper.deleteType(id);
        String msg;
        if (count == 0 ) {
            msg = "删除失败";
        } else {
            msg = "删除成功";
        }
        return msg;
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }
}
