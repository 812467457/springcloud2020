package cn.yylm.springcloud.service;

import cn.yylm.springcloud.entity.CommonResult;
import cn.yylm.springcloud.entity.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")   //指定提供接口的微服务名称
public interface OrderFeignService {
    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/provider/feign/timeout")
    public String feignTimeout();
}
