package com.xxq.filemanager.service.interfaceI;

import com.xxq.filemanager.bean.ArchivesInfo;

import java.util.List;

public interface ArchivesService {
    List<ArchivesInfo> queryAllArch();
    List<ArchivesInfo> queryArchByPara(ArchivesInfo archivesInfo);
}
