package cn.yylm.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.netflix.hystrix.HystrixProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentService {

    //服务降级

    public String paymentInfo_success(Long id) {
        return "线程池" + Thread.currentThread().getName() + ":paymentInfo_success:" + id;
    }

    /**
     * 模拟异常，使用Hystrix熔断降级
     *
     * @param id
     * @return
     */
    //配置如果出现异常就会执行兜底方法
    @HystrixCommand(fallbackMethod = "paymentInfo_failed_handler", commandProperties = {
            //指定超时时间最大多少，超出后异常
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_failed(Long id) {
        //如果不是超时异常，而是其他异常，也会使用上面指定的兜底方法
        //int i = 1 / 0

        int timeNumber = 5000;
        try {
            Thread.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池" + Thread.currentThread().getName() + ":paymentInfo_failed:" + id + "：时间：" + timeNumber;
    }

    //兜底方法
    public String paymentInfo_failed_handler(Long id) {
        return "线程池" + Thread.currentThread().getName() + ":paymentInfo_failed_handler:" + id;
    }

    //服务熔断

    /**
     * 表示在10秒之内的10次请求，如果有百分之60的都是失败的，那么将触发断路器，开启断路器后会熔断所有请求（包括成功的）一小会。
     * 之后进入半开状态，当请求成功率符合规则了就会关闭断路器。
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),   //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),  //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),    //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")     //失败率达到多少触发断路器
    })
    public String paymentCircuitBreaker(Long id) {
        if (id < 0) {
            throw new RuntimeException("id不能小于0");
        }
        String simpleUUID = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + simpleUUID;
    }

    public String paymentCircuitBreaker_fallback(Long id){
        return "id 不能为负数" + id;
    }
}
