package cn.yylm.springcloud.contorller;

import cn.yylm.springcloud.service.IMessageProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {
    @Resource
    private IMessageProviderService iMessageProviderService;

    @GetMapping("/sendMessage")
    public String sendMessage(){
        return iMessageProviderService.send();
    }
}
