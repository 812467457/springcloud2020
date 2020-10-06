package cn.yylm.springcloud.service;

import cn.yylm.springcloud.entity.CommonResult;
import cn.yylm.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
//指定提供接口的微服务名称,指定降级服务的类,该类必须实现当前类的具体降级方法，当提供服务的微服务宕机，就会使用当前降级
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = OrderFallbackService.class)
public interface OrderFeignService {
    @GetMapping("/payment/hystrix/success/{id}")
    String paymentInfo_success(@PathVariable("id") Long id);

    @GetMapping("/payment/hystrix/failed/{id}")
    String paymentInfo_failed(@PathVariable("id") Long id);
}
