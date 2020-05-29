package com.xxq.filemanager.mapper;

import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.entity.ArchPathEntity;
import com.xxq.filemanager.entity.ArchTypeEntity;
import com.xxq.filemanager.entity.ArchivesEntity;
import com.xxq.filemanager.entity.FileTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArchivesMapper {
    List<ArchivesEntity> selectAllArch();

    List<ArchivesEntity> selectArchByPara(ArchivesEntity archivesEntity);

    void deleteFile(ArchivesEntity archivesEntity);

    void insertOne(ArchivesEntity archivesEntity);

    List<ArchivesEntity> selectByArchClassId(Integer classId);

    List<ArchTypeEntity> queryAllType();

    boolean insertType(ArchTypeEntity archTypeEntity);

    boolean deletOneType(ArchTypeEntity archTypeEntity);
    boolean updateArchClassId(ArchivesEntity archivesEntity);

    boolean updateType(ArchTypeEntity archTypeEntity);

    int updateBorStatusByArchNo(ArchivesEntity archivesEntity);
    int insertPath(ArchPathEntity archPathEntity);

    ArchTypeEntity queryTypeByTypeNo(Integer typeNo);

    String findPath(String archNo);
}

