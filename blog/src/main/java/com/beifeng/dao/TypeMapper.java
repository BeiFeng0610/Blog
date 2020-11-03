package com.beifeng.dao;

import com.beifeng.domain.Type;

import java.util.List;

public interface TypeMapper {

    Integer saveType(Type type);

    Type getTypeById(Long id);

    List<Type> getAllType();

    Integer updateType(String name,Long id);

    Integer deleteType(Long id);

    Type getTypeByName(String name);
}