package com.wasai.controller;

import com.wasai.entities.PayDTO;
import com.wasai.resp.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author 骑猪去青岛
 * @data 2024-06-30
 * @time 18:11
 * @desc
 */
@Slf4j
@Tag(name = "订单服务")
@RestController
@RequestMapping("/consumer")
public class OrderController {
//    public static final String PaymentSrv_URL = "http://localhost:8001";//先写死，硬编码
    public static final String PaymentSrv_URL = "http://cloud-payment-service";//服务注册中心上的微服务名称

    @Resource
    private RestTemplate restTemplate;

    //新增
    @Operation(summary = "新增", parameters = {
            @Parameter(in = ParameterIn.DEFAULT, name = "payNo", description = "支付编号", required = true, example = "XX"),
            @Parameter(in = ParameterIn.DEFAULT, name = "orderNo", description = "订单编号", required = true, example = "XX"),
            @Parameter(in = ParameterIn.DEFAULT, name = "userId", description = "用户编号", required = true, example = "XX"),
            @Parameter(in = ParameterIn.DEFAULT, name = "amount", description= "金额", required = true, example = "XX")
    })
    @RequestMapping(value = "/pay/add",method = RequestMethod.GET)
    public ResultData addOrder(PayDTO payDTO) {
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
    }

    //查询
    @GetMapping(value = "/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id, ResultData.class);
    }

    //删除
    @GetMapping(value = "/pay/delete/{id}")
    public ResultData deletePayInfo(@PathVariable("id") Integer id) {
        restTemplate.delete(PaymentSrv_URL + "/pay/delete/" + id);
        return ResultData.success("删除成功");
    }

    //修改
    @GetMapping(value = "/pay/update")
    public ResultData updatePayInfo(PayDTO payDTO) {
        restTemplate.put(PaymentSrv_URL + "/pay/update", payDTO);
        return ResultData.success("修改成功");
    }

    //获取全部订单
    @GetMapping(value = "/pay/getAll")
    public ResultData getAllPay() {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/getAll", ResultData.class);
    }
    @GetMapping(value = "/pay/get/info")
    public String getInfoByConsul() {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/value", String.class);
    }

    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        log.info("discoveryClient.getServices():"+services);
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }
}
