package cn.yylm.springcloud.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * 使用配置类方法配置网关路由
 */
@SpringBootConfiguration
public class GatewayConfig {
    /**
     * 配置了一个路由名字为path_route_yylm的规则
     * 访问http://localhost:9527/guonei跳转到百度新闻国内新闻
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_yylm",r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
}
