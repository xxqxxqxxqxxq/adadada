package com.xxq.filemanager.mapper;

import com.xxq.filemanager.entity.DepartEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartMapper {
    List<DepartEntity> queryDepartAll();

    void insertOne(String departName);

    int deleteOne(String departName);
}
