package net.sinlo.vchat.config;

import net.sinlo.vchat.websocket.RedisMessageListener;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @Description 消息订阅配置类
 * @Author jie.zhao
 * @Date 2020/3/31 13:54
 */
@Configuration
public class RedisSubscriberConfig {
    @Bean
    MessageListenerAdapter listenerAdapter(RedisMessageListener receiver) {
        return new MessageListenerAdapter(receiver);
    }


    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        LettuceConnectionFactory lettuceConnectionFactory = (LettuceConnectionFactory) connectionFactory;
        //设置存储的节点
        lettuceConnectionFactory.setDatabase(0);
        container.setConnectionFactory(lettuceConnectionFactory);
        //这里要设定监听的主题是chat
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
        return container;
    }
}