package com.cobby.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisSentinelConfig {
    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

//    @Value("${redis.password}")
//    private String redisPassword;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisHost, redisPort);
//        config.setPassword(RedisPassword.of(redisPassword));

        // Sentinel 구성 config
        org.springframework.data.redis.connection.RedisSentinelConfiguration  redisSentinelConfiguration =
                new org.springframework.data.redis.connection.RedisSentinelConfiguration()
                        .master("mymaster")
                        .sentinel(redisHost, 26379)
                        .sentinel(redisHost, 26380)
                        .sentinel(redisHost, 26381);

//      redisSentinelConfiguration.setPassword("foobared");

        return new LettuceConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, ?> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(this.redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        //redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(UserEntity.class)); // <- 주고 받을 데이터(Entity)를 미리 설정 할때
        return redisTemplate;
    }
}
