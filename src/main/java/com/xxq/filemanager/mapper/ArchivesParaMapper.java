package com.xxq.filemanager.mapper;

import com.xxq.filemanager.entity.ArchivesParaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName ArchivesParaMapper
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/9 19:26
 * @Version V1.0
 **/
@Mapper
public interface ArchivesParaMapper {
    ArchivesParaEntity selectParaByArchID(String id);

    void insertOne(ArchivesParaEntity entityFromInfo);
}
