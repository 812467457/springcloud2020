package cn.yylm.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 指定负载均衡规则的类，注意该类不能再ComponentScan扫描下
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        //随机策略
        return new RandomRule();
    }
}
