package cn.yylm.springcloud.controller;

import cn.yylm.springcloud.entity.CommonResult;
import cn.yylm.springcloud.entity.Payment;
import cn.yylm.springcloud.service.OrderFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//配置全局的降级方法
@DefaultProperties(defaultFallback = "paymentInfo_Global_FallbackMethod")
public class OrderFeignController {
    @Resource
    private OrderFeignService orderFeignService;

    @GetMapping("/consumer/payment/hystrix/success/{id}")
    public String paymentInfo_success(@PathVariable("id") Long id) {
        String result = orderFeignService.paymentInfo_success(id);
        log.info(result);
        return result;
    }


    /* @HystrixCommand(fallbackMethod = "paymentInfo_failed_handler", commandProperties = {
             @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
     })*/
    @HystrixCommand //如果没有特别指定就使用默认的降级
    @GetMapping("/consumer/payment/hystrix/failed/{id}")
    public String paymentInfo_failed(@PathVariable("id") Long id) {
        int i = 1 / 0;
        String result = orderFeignService.paymentInfo_failed(id);
        log.info(result);
        return result;
    }

    //客户端的降级方法
    public String paymentInfo_failed_handler(Long id) {
        return "线程池" + Thread.currentThread().getName() + ":consumer paymentInfo_failed_handler:" + id;
    }

    /**
     * 全局的降级方法，带返回值和参数有问题
     * @return
     */
    public String paymentInfo_Global_FallbackMethod() {
        return "consumer paymentInfo_global_fallbackMethod:";
    }
}
