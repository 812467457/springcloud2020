package cn.yylm.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
public class OrderFallbackService implements OrderFeignService {
    @Override
    @ResponseBody
    public String paymentInfo_success(Long id) {
        return "OrderFallbackService_paymentInfo_success";
    }

    @Override
    @ResponseBody
    public String paymentInfo_failed(Long id) {
        return "OrderFallbackService_paymentInfo_failed";
    }



}
