package com.xxq.filemanager.service.interfaceI;

import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.bean.ArchivesParaInfo;

import java.util.List;

public interface ArchivesService {
    List<ArchivesInfo> queryAllArch();
    List<ArchivesInfo> queryArchByPara(ArchivesInfo archivesInfo);
    ArchivesParaInfo queryArchParaByAID(Integer id);
    void deleteOneFile(List<ArchivesInfo> archivesInfoList);

    void insertOneFile(ArchivesInfo archivesInfo);

    void insertOneFilePara(ArchivesParaInfo archivesParaInfo);
}
