package com.beifeng.service.impl;

import com.beifeng.dao.TypeMapper;
import com.beifeng.domain.Type;
import com.beifeng.service.TypeService;
import com.beifeng.util.DateTimeUtil;
import com.beifeng.util.UUIDUtil;
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
        type.setId(UUIDUtil.getUUID());
        type.setCreateTime(DateTimeUtil.getSysTime());
        type.setUpdateTime(DateTimeUtil.getSysTime());

        return typeMapper.saveType(type);
    }

    @Transactional
    @Override
    public Type getType(String id) {
        return typeMapper.getTypeById(id);
    }

    @Transactional
    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }

    @Transactional
    @Override
    public String updateType(String name,String id) {
        String msg;

        Type type = new Type();
        type.setId(id);
        type.setName(name);
        type.setUpdateTime(DateTimeUtil.getSysTime());
        Integer count = typeMapper.updateType(type);

        if (count == 0 ) {
            msg = "修改失败";
        } else {
            msg = "修改成功";
        }

        return msg;
    }

    @Transactional
    @Override
    public String deleteType(String id) {

        String msg;
        /*
            查询分类下有多少博客，
                博客为0，可直接删除，
                博客不为0，不可删除此分类
        */
        Integer blogCount = typeMapper.getBlogCountByTypeId(id);
        if (blogCount>0){
            msg = "请先删除分类下所有博客";
            return msg;
        }

        Integer count = typeMapper.deleteType(id);
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
