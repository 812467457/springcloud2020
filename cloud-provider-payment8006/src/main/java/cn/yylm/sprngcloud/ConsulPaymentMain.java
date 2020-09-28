package cn.yylm.sprngcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulPaymentMain {
    public static void main(String[] args) {
        SpringApplication.run(ConsulPaymentMain.class, args);
    }
}
