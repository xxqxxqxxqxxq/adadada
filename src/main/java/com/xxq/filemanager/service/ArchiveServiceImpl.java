package com.xxq.filemanager.service;

import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.bean.ArchivesParaInfo;
import com.xxq.filemanager.entity.ArchTypeEntity;
import com.xxq.filemanager.entity.ArchivesEntity;
import com.xxq.filemanager.entity.ArchivesParaEntity;
import com.xxq.filemanager.entity.FileTypeEntity;
import com.xxq.filemanager.mapper.ArchivesMapper;
import com.xxq.filemanager.mapper.ArchivesParaMapper;
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
    @Autowired
    ArchivesParaMapper archivesParaMapper;
    @Override
    public List<ArchivesInfo> queryAllArch() {
        return archEntityToInfo(archivesMapper.selectAllArch());
    }

    @Override
    public List<ArchivesInfo> queryArchByPara(ArchivesInfo archivesInfo) {
        List<ArchivesEntity> archivesEntities = archivesMapper.selectArchByPara(ArchivesEntity.createEntityFromInfo(archivesInfo));
        return archEntityToInfo(archivesEntities);
    }

    @Override
    public List<ArchivesInfo> queryArchByClassId(ArchivesInfo archivesInfo) {
        return archEntityToInfo(archivesMapper.selectByArchClassId(archivesInfo.getClassId()));

    }

    /**
     * 根据档案id查询，档案参数
     *
     * @param id
     * @return
     */
    @Override
    public ArchivesParaInfo queryArchParaByAID(Integer id) {
        return ArchivesParaInfo.createInfoFromEntity(archivesParaMapper.selectParaByArchID(id));
    }

    @Override
    public void deleteOneFile(List<ArchivesInfo> archivesInfos) {
        for(ArchivesInfo a : archivesInfos){
            archivesMapper.deleteFile(a.getArchNo());
        }

    }

    @Override
    public void insertOneFile(ArchivesInfo archivesInfo) {
        archivesMapper.insertOne(ArchivesEntity.createEntityFromInfo(archivesInfo));
    }

    @Override
    public void insertOneFilePara(ArchivesParaInfo archivesParaInfo) {
        archivesParaMapper.insertOne(ArchivesParaEntity.createEntityFromInfo(archivesParaInfo));
    }

    @Override
    public List<ArchTypeEntity> queryAllType() {
        return archivesMapper.queryAllType();
    }

    @Override
    public boolean addOneType(ArchTypeEntity archTypeEntity) {
        return archivesMapper.insertType(archTypeEntity);
    }

    @Override
    public void deleteOneType(ArchTypeEntity archTypeEntity) {
        archivesMapper.deletOneType(archTypeEntity);
    }

    @Override
    public boolean updateOneType(ArchTypeEntity archTypeEntity) {
        return archivesMapper.updateType(archTypeEntity);
    }

    @Override
    public boolean updateArchClassId(ArchivesEntity archivesEntity) {
        return archivesMapper.updateArchClassId(archivesEntity);
    }

    @Override
    public int updateBorStatus(ArchivesEntity archivesEntity) {
        return archivesMapper.updateBorStatusByArchNo(archivesEntity);
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
