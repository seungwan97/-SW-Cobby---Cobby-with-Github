package com.cobby.main.common.config;

import com.cobby.main.costume.api.dto.response.CostumeGetResponse;
import com.cobby.main.quest.api.dto.response.QuestGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@RequiredArgsConstructor
@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.password}")
    private String redisPass;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration()
                .master("mymaster")
                .sentinel(redisHost, 26379)
                .sentinel(redisHost, 26380)
                .sentinel(redisHost, 26381);

        redisSentinelConfiguration.setPassword(redisPass);
        return new LettuceConnectionFactory(redisSentinelConfiguration);  // Lettuce 사용
    }

    @Bean
    public RedisTemplate<String, Integer> redisTemplate() {
        RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());   // Key: String
        // redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Integer.class));  // Value: 직렬화에 사용할 Object 사용하기
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, QuestGetResponse> redisQuestTemplate() {
        RedisTemplate<String, QuestGetResponse> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());   // Key: String
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(QuestGetResponse.class));  // Value: 직렬화에 사용할 Object 사용하기
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, CostumeGetResponse> redisCostumeTemplate() {
        RedisTemplate<String, CostumeGetResponse> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());   // Key: String
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(CostumeGetResponse.class));  // Value: 직렬화에 사용할 Object 사용하기
        return redisTemplate;
    }
}