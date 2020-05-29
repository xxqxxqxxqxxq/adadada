package com.xxq.filemanager.service.interfaceI;

import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.bean.ArchivesParaInfo;
import com.xxq.filemanager.entity.ArchPathEntity;
import com.xxq.filemanager.entity.ArchTypeEntity;
import com.xxq.filemanager.entity.ArchivesEntity;
import com.xxq.filemanager.entity.FileTypeEntity;

import java.util.List;

public interface ArchivesService {
    List<ArchivesInfo> queryAllArch();
    List<ArchivesInfo> queryArchByPara(ArchivesInfo archivesInfo);
    List<ArchivesInfo> queryArchByClassId(ArchivesInfo archivesInfo);
    ArchivesParaInfo queryArchParaByAID(String id);
    void deleteOneFile(List<ArchivesInfo> archivesInfoList);

    void insertOneFile(ArchivesInfo archivesInfo);

    void insertOneFilePara(ArchivesParaInfo archivesParaInfo);
    List<ArchTypeEntity> queryAllType();

    boolean addOneType(ArchTypeEntity archTypeEntity);

    boolean deleteOneType(ArchTypeEntity archTypeEntity);

    boolean updateOneType(ArchTypeEntity archTypeEntity);
    boolean updateArchClassId(ArchivesEntity archivesEntity);

    int updateBorStatus(ArchivesEntity archivesEntity);

    int insertArchPath(ArchPathEntity archPathEntity);

    ArchTypeEntity queryArchType(Integer typeNo);

    String findPath(String value);
}
