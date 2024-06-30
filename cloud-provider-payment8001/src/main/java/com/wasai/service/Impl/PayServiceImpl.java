package com.wasai.service.Impl;

import com.wasai.entities.Pay;
import com.wasai.mapper.PayMapper;
import com.wasai.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 骑猪去青岛
 * @data 2024-06-30
 * @time 10:46
 * @desc
 */
@Service
public class PayServiceImpl implements PayService {
    @Resource
//    相当于@Autowired
    private PayMapper payMapper;
    @Override
    public int add(Pay pay) {
        return payMapper.insertSelective(pay);
    }

    @Override
    public int delete(Integer id) {
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Pay pay) {
        return payMapper.updateByPrimaryKeySelective(pay);
    }


    @Override
    public Pay getById(Integer id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> getAll() {
        return payMapper.selectAll();
    }
}
