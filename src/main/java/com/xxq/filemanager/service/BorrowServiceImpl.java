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
}
