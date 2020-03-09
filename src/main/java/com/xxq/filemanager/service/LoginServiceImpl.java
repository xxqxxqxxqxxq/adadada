package com.xxq.filemanager.service;

import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.mapper.SysUserMapper;
import com.xxq.filemanager.entity.SysUserEntity;
import com.xxq.filemanager.service.interfaceI.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName LoginServiceImpl
 * @Description: TODO
 * @Author xxq
 * @Date 2020/1/16 14:45
 * @Version V1.0
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Override
    public SysUserInfo login(String username, String password) {
        SysUserEntity sysUserEntity = sysUserMapper.login(username, password);
        if (sysUserEntity==null){
            return null;
        }
        System.out.println(sysUserEntity);
        return  SysUserInfo.createFromEntity(sysUserEntity);
    }
}
