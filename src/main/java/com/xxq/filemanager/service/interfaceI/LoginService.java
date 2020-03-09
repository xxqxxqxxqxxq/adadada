package com.xxq.filemanager.service.interfaceI;

import com.xxq.filemanager.bean.SysUserInfo;

public interface LoginService {
    SysUserInfo login(String username,String password);
}
