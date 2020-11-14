package com.beifeng.dao;

import com.beifeng.domain.Type;

import java.util.List;

public interface TypeMapper {

    Integer saveType(Type type);

    Type getTypeById(String id);

    List<Type> getAllType();

    Integer updateType(Type type);

    Integer deleteType(String id);

    Type getTypeByName(String name);

    Integer getBlogCountByTypeId(String id);
}