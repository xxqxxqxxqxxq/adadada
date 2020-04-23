package com.xxq.filemanager.service;

import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.mapper.SysUserMapper;
import com.xxq.filemanager.entity.SysUserEntity;
import com.xxq.filemanager.service.interfaceI.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author xxq
 * @Date 2020/2/10 18:00
 * @Version V1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    SysUserMapper sysUserMapper;
    @Override
    public List<SysUserInfo> queryUserByPara(String departName, Integer id, String username) {
        List<SysUserInfo> sysUserInfos= new ArrayList<>();
        List<SysUserEntity> sysUserEntities = sysUserMapper.queryUserByPara(departName, id, username);
        sysUserEntities.forEach(x->
            sysUserInfos.add(SysUserInfo.createFromEntity(x))

        );
        return sysUserInfos;
    }
    public List<SysUserInfo> queryUserByDID( Integer departId) {
        List<SysUserInfo> sysUserInfos= new ArrayList<>();
        List<SysUserEntity> sysUserEntities = sysUserMapper.queryUserByDepartId(departId);
        sysUserEntities.forEach(x->
                sysUserInfos.add(SysUserInfo.createFromEntity(x))

        );
        return sysUserInfos;
    }
    @Override
    public void deleteOne(List<SysUserInfo> userInfos) {
        for(SysUserInfo sysUserInfo:userInfos){
            sysUserMapper.deleteUser(sysUserInfo.getId());
        }

    }

    /**
     * 修改用户信息
     * @param sysUserInfo
     */
    @Override
    public void updateUser(SysUserInfo sysUserInfo) {
        sysUserMapper.updateUser(SysUserEntity.createFromInfo(sysUserInfo));
    }

    @Override
    public void insertUser(SysUserInfo sysUserInfo) {
        sysUserMapper.insertUser(SysUserEntity.createFromInfo(sysUserInfo));
    }

}
