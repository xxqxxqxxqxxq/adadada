package com.xxq.filemanager.mapper;

import com.xxq.filemanager.entity.ArchivesEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArchivesMapper {
        List<ArchivesEntity> selectAllArch();
        List<ArchivesEntity> selectArchByPara(ArchivesEntity archivesEntity);
}
