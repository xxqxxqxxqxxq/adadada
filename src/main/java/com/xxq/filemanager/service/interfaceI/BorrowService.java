package com.xxq.filemanager.service.interfaceI;

import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.entity.BorrowEntity;

import java.util.List;

/**
 * @ClassName BorrowService
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/22 16:23
 * @Version V1.0
 **/
public interface BorrowService {
    List<BorrowEntity> queryAll();
    Integer toApprove();
    List<BorrowEntity> queryByUserId(Integer userId);
    List<BorrowEntity> queryAllToApprove();
    int addOne(BorrowEntity borrowEntity);

    void approve(BorrowEntity borrowEntity);
}
