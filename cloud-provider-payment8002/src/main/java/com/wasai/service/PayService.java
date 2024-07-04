package com.wasai.service;

import com.wasai.entities.Pay;

import java.util.List;

/**
 * @author 骑猪去青岛
 * @data 2024-06-30
 * @time 10:44
 * @desc
 */
public interface PayService {
    public int add(Pay pay);

    public int delete(Integer id);

    public  int update(Pay pay);


    public Pay getById(Integer id);

    public List<Pay> getAll();

}
