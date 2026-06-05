package com.yashdev.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration// This annotation indicates that the class is a source of bean definitions for the application context.
// It allows Spring to recognize this class as a configuration class and process the bean definitions within it.
public class RedisConfig {

    @Bean
    // what this method does is when we create this method we can get the key value created in redis cli in our spring boot application
    // and we can also set the key value in our spring boot application and we can get that key value in redis cli

    //Basically it makes the use of same serializer and deserializer for both the redis clis and spring application
    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
        RedisTemplate redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }
}
