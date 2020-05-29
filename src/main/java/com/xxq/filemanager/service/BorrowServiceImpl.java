package com.xxq.filemanager.service;

import com.xxq.filemanager.entity.BorrowEntity;
import com.xxq.filemanager.mapper.BorrowMapper;
import com.xxq.filemanager.service.interfaceI.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName BorrowServiceImpl
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/22 16:24
 * @Version V1.0
 **/
@Service
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    BorrowMapper borrowMapper;
    @Override
    public List<BorrowEntity> queryAll() {
        return borrowMapper.queryAll();
    }

    @Override
    public Integer toApprove() {
        return borrowMapper.amountA();
    }

    @Override
    public List<BorrowEntity> queryByUserId(Integer userId) {
        return borrowMapper.selectByUserId(userId);
    }

    /**
     * 查询所有待审批的请求
     * @return
     */
    @Override
    public List<BorrowEntity> queryAllToApprove() {
        return borrowMapper.queryAllToApprove();
    }

    @Override
    public int addOne(BorrowEntity borrowEntity) {
       return borrowMapper.insertOne(borrowEntity);
    }

    @Override
    public void approve(BorrowEntity borrowEntity) {
        borrowMapper.passOne(borrowEntity);
    }

    @Override
    public BorrowEntity queryByArchNo(String archNo) {
        return borrowMapper.queryByArch(archNo);
    }

    @Override
    public int backArch(String archNo) {
        return borrowMapper.back(archNo);
    }
}
