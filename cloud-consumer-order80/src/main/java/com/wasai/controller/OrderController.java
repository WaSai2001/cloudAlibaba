package com.wasai.controller;

import com.wasai.entities.PayDTO;
import com.wasai.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author 骑猪去青岛
 * @data 2024-06-30
 * @time 18:11
 * @desc
 */
@RestController
public class OrderController {
//    public static final String PaymentSrv_URL = "http://localhost:8001";//先写死，硬编码
    public static final String PaymentSrv_URL = "http://cloud-payment-service";//服务注册中心上的微服务名称

    @Resource
    private RestTemplate restTemplate;

    //新增
    @GetMapping(value = "/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO) {
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
    }

    //查询
    @GetMapping(value = "/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id, ResultData.class);
    }

    //删除
    @GetMapping(value = "/consumer/pay/delete/{id}")
    public ResultData deletePayInfo(@PathVariable("id") Integer id) {
        restTemplate.delete(PaymentSrv_URL + "/pay/delete/" + id);
        return ResultData.success("删除成功");
    }

    //修改
    @GetMapping(value = "/consumer/pay/update")
    public ResultData updatePayInfo(PayDTO payDTO) {
        restTemplate.put(PaymentSrv_URL + "/pay/update", payDTO);
        return ResultData.success("修改成功");
    }

    //获取全部订单
    @GetMapping(value = "/consumer/pay/getAll")
    public ResultData getAllPay() {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/getAll", ResultData.class);
    }
}
