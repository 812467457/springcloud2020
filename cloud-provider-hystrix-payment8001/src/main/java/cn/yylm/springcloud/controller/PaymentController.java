package cn.yylm.springcloud.controller;

import cn.yylm.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/success/{id}")
    public String paymentInfo_success(@PathVariable("id") Long id) {
        String result = paymentService.paymentInfo_success(id);
        log.info(result);
        return result;
    }

    @GetMapping("/payment/hystrix/failed/{id}")
    public String paymentInfo_failed(@PathVariable("id") Long id) {
        String result = paymentService.paymentInfo_failed(id);
        log.info(result);
        return result;
    }


    //熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Long id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info(result);
        return result;
    }
}
