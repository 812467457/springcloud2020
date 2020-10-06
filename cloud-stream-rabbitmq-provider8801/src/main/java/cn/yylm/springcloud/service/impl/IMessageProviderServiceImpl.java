package cn.yylm.springcloud.service.impl;

import cn.hutool.core.lang.UUID;
import cn.yylm.springcloud.service.IMessageProviderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;


import javax.annotation.Resource;

@EnableBinding(Source.class)    //定义消息的推送管道
public class IMessageProviderServiceImpl implements IMessageProviderService {
    @Resource
    @Qualifier(value = "output")
    private MessageChannel output; //消息发送管道

    @Override
    public String send() {
        String msg = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(msg).build());
        System.out.println(msg);
        return null;
    }
}
