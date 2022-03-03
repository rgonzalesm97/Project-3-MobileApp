package com.example.BootcampMsMobileApp.config;

import com.example.BootcampMsMobileApp.model.Yanki;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {
	
    @Bean
    ReactiveRedisOperations<String, Yanki> redisOperations(ReactiveRedisConnectionFactory factory) {
    	
    Jackson2JsonRedisSerializer<Yanki> serializer = new Jackson2JsonRedisSerializer<>(Yanki.class);

    RedisSerializationContext.RedisSerializationContextBuilder<String, Yanki> builder =
        RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

    RedisSerializationContext<String, Yanki> context = builder.value(serializer).build();

    return new ReactiveRedisTemplate<>(factory, context);
  }

}
