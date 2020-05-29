package com.xxq.filemanager.service.interfaceI;

import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.entity.SysUserEntity;

import java.util.List;

/**
 * @ClassName UserService
 * @Description: TODO
 * @Author xxq
 * @Date 2020/2/10 17:59
 * @Version V1.0
 **/
public interface UserService {
    List<SysUserInfo> queryUserByPara(String departName, Integer id, String username);
    void deleteOne(List<SysUserInfo> userInfos);
    void updateUser(SysUserInfo sysUserInfo);
    void insertUser(SysUserInfo sysUserInfo);
    List<SysUserInfo> queryUserByDID( Integer departId);

    int updateUserInfo(SysUserEntity sysUserEntity);

    int updatePhoto(SysUserEntity sysUserEntity);
}
