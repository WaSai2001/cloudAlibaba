package com.wasai.controller;

import com.wasai.entities.Pay;
import com.wasai.entities.PayDTO;
import com.wasai.resp.ResultData;
import com.wasai.resp.ReturnCodeEnum;
import com.wasai.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 骑猪去青岛
 * @data 2024-06-30
 * @time 10:52
 * @desc
 */
@RestController
@Slf4j
@Tag(name = "支付服务模块", description = "支付CRUD")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping(value = "pay/add")
    @Operation(summary = "添加", description = "添加支付信息")
    @Parameters({
            @Parameter(name = "payNo", description = "支付编号", required = true),
            @Parameter(name = "orderNo", description = "订单编号", required = true),
            @Parameter(name = "userId", description = "用户编号", required = true),
            @Parameter(name = "amount", description = "金额", required = true)
    })
    public ResultData<String> addPay(@RequestBody Pay pay) {
        System.out.println(pay.toString());
        int i = payService.add(pay);
        return ResultData.success("成功添加记录，返回值：" + i);
    }

    @DeleteMapping(value = "pay/delete/{id}")
    @Operation(summary = "删除", description = "删除支付信息")
    public ResultData<String> deletePay(@PathVariable("id") Integer id) {
        int i = payService.delete(id);
        return ResultData.success("成功删除记录，返回值：" + i);
    }

    @PutMapping(value = "pay/update")
    @Operation(summary = "更新", description = "更新支付信息")
    public ResultData<String> updatePay(@RequestBody PayDTO paydto) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(paydto, pay);

        int i = payService.update(pay);

        return ResultData.success("成功更新记录，返回值：" + i);
    }

    @GetMapping(value = "pay/get/{id}")
    @Operation(summary = "根据id查询", description = "根据id查询支付信息")
    public ResultData<Pay> getPayById(@PathVariable("id") Integer id) {
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping(value = "pay/getAll")
    @Operation(summary = "查询所有", description = "查询所有支付信息")
    public ResultData<List> getAllPay() {
        try{
            List<Pay> payList = payService.getAll();
            return ResultData.success(payList);
        }catch (Exception e){
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
    }

}
