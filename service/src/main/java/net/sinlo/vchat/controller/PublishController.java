package net.sinlo.vchat.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.sinlo.vchat.service.impl.SendMessageService;
import net.sinlo.vchat.websocket.RedisMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/redis/")
public class PublishController {
    /**
     * 注入刚才redisConfig配置的监听
     */
    @Autowired
    RedisMessageListenerContainer redisMessageListenerContainer;

    @Autowired
    private RedisMessageListener redisMessageListener;

    @Autowired
    SendMessageService messageService;


    /***
     * 一次向主题里面发送一条消息
     * @param message
     * @return
     */
    @GetMapping("/publish")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "message", required = true, paramType = "query", dataType = "String")
    })
    public String publicMessage(@RequestParam("message") String message) {
        messageService.sendMessage("chat", message);
        return "success";
    }


    /***
     * 订阅主题
     * @param subscribeTopic
     * @param userId
     * @return
     */
    @GetMapping("/subscribe")
    @ResponseBody
    public String subscribe(
            @RequestParam("subscribeTopic") String subscribeTopic, @RequestParam(value = "userId", required = true) String userId) {
        System.out.println("用户ID是的:   " + userId + "    订阅了话题：" + subscribeTopic);
        //设置监听listener
        redisMessageListener = new RedisMessageListener();
        redisMessageListener.setUserId(userId);
        //设置订阅topic
        redisMessageListenerContainer.addMessageListener(redisMessageListener, new PatternTopic(subscribeTopic));
        return "success";
    }
}
