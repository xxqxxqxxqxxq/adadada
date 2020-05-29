package com.xxq.filemanager.mapper;

import com.xxq.filemanager.entity.BorrowEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName BorrowMapper
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/22 15:31
 * @Version V1.0
 **/
@Mapper
public interface BorrowMapper {
    List<BorrowEntity> queryAll();
    Integer amountA();
    List<BorrowEntity> selectByUserId(Integer archivesId);
    List<BorrowEntity> queryAllToApprove();
    int insertOne(BorrowEntity borrowEntity);

    void passOne(BorrowEntity borrowEntity);

    BorrowEntity queryByArch(String archNo);

    int back(String archNo);
}
