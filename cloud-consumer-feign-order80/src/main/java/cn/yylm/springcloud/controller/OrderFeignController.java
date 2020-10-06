package cn.yylm.springcloud.controller;

import cn.yylm.springcloud.entity.CommonResult;
import cn.yylm.springcloud.entity.Payment;
import cn.yylm.springcloud.service.OrderFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {
    @Resource
    private OrderFeignService orderFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return orderFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/feign/timeout")
    public String feignTimeout() {
        //调用超时方法测试，openFeign默认超时时间为1秒，1秒无响应就报异常，在配置文件修改
        return orderFeignService.feignTimeout();
    }
}
