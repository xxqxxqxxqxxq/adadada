package com.xxq.filemanager.service.interfaceI;

import com.xxq.filemanager.bean.DepartInfo;
import com.xxq.filemanager.entity.DepartEntity;

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
    int updateAmount(DepartEntity departEntity);
    int queryAmountByDepartName(String departName);

    int deleteUserByDId(Integer departId);
}
