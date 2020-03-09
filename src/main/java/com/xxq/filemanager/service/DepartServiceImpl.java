package com.xxq.filemanager.service;

import com.xxq.filemanager.bean.DepartInfo;
import com.xxq.filemanager.mapper.DepartMapper;
import com.xxq.filemanager.entity.DepartEntity;
import com.xxq.filemanager.service.interfaceI.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DepartServiceImpl
 * @Description: 机构管理业务实现
 * @Author xxq
 * @Date 2020/2/26 17:19
 * @Version V1.0
 **/
@Service
public class DepartServiceImpl implements DepartService {
    @Autowired
    DepartMapper departMapper;
    @Override
    public List<DepartInfo> selectAllDepart() {
        List<DepartInfo> list = new ArrayList<>();
        List<DepartEntity> departEntities = departMapper.queryDepartAll();
        departEntities.forEach(x->
                list.add(DepartInfo.createFromEntity(x))
        );
        return list;
    }
}
