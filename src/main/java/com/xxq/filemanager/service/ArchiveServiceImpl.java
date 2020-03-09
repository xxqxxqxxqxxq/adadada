package com.xxq.filemanager.service;

import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.entity.ArchivesEntity;
import com.xxq.filemanager.mapper.ArchivesMapper;
import com.xxq.filemanager.service.interfaceI.ArchivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ArchiveServiceImpl
 * @Description: 档案管理对应的业务层
 *
 * @Author xxq
 * @Date 2020/3/5 19:23
 * @Version V1.0
 **/
@Service
public class ArchiveServiceImpl implements ArchivesService {
    @Autowired
    ArchivesMapper archivesMapper;
    @Override
    public List<ArchivesInfo> queryAllArch() {
        return archEntityToInfo(archivesMapper.selectAllArch());
    }

    @Override
    public List<ArchivesInfo> queryArchByPara(ArchivesInfo archivesInfo) {
        List<ArchivesEntity> archivesEntities = archivesMapper.selectArchByPara(ArchivesEntity.createEntityFromInfo(archivesInfo));
        return archEntityToInfo(archivesEntities);
    }

    /**
     * 将List<ArchivesEntity>转化为List</ArchivesInfo>
     * @return
     */
    public List<ArchivesInfo> archEntityToInfo(List<ArchivesEntity> archivesEntities)  {
        List<ArchivesInfo> archivesInfoList = new ArrayList<>();
        archivesEntities.forEach(x->
                archivesInfoList.add(ArchivesInfo.createInfoFromEntity(x))

        );
        return archivesInfoList;
    }
}
