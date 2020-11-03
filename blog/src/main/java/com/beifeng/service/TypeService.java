package com.beifeng.service;

import com.beifeng.domain.Type;

import java.util.List;


/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/2 15:38
 */
public interface TypeService {

    Integer saveType(Type type);

    Type getType(Long id);

    List<Type> getAllType();

    String updateType(String name,Long id);

    String deleteType(Long id);

    Type getTypeByName(String name);

}
