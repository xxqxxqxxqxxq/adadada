package com.xxq.filemanager.service.interfaceI;

import com.xxq.filemanager.bean.DepartInfo;

import java.util.List;

/**
 * @ClassName DepartService
 * @Description: TODO
 * @Author xxq
 * @Date 2020/2/26 17:19
 * @Version V1.0
 **/
public interface DepartService {
    List<DepartInfo> selectAllDepart();

    void addOneDepart(String departName);

    int deleteOne(String departName);
}
