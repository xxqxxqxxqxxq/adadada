package com.xxq.filemanager.mapper;

import com.xxq.filemanager.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {
    SysUserEntity login(@Param("username") String username,@Param("password") String password);
    List<SysUserEntity> queryUserByPara(String departName,Integer id,String username);
    void deleteUser(Integer id);
    void updateUser(SysUserEntity sysUserEntity);
    int insertUser(SysUserEntity sysUserEntity);
}
