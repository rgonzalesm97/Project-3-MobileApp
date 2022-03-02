
package com.example.BootcampMsMobileApp.service.impl;
import com.example.BootcampMsMobileApp.model.Yanki;
import com.example.BootcampMsMobileApp.service.YankiService;


import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.kafka.core.KafkaTemplate;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@AllArgsConstructor
@Service
public class YankiServiceImpl implements YankiService {
    private final ReactiveRedisOperations<String, Yanki> yankiOpps;
    private final KafkaTemplate<String, Yanki> kafkaTemplate;

 
    @Override
    @SendTo
    public boolean sendPayments(Yanki yanki) {
       
        this.kafkaTemplate.send("xc73qeeh-default", yanki);
        return true;
        //return source.send("data", MessageBuilder.withPayload(yanki).build());

        
    }

    @Override
    public void recibePayments() {
        
    }

    @Override
    public Flux<Yanki> findAll() {
        return yankiOpps.keys("*")
        .flatMap(yankiOpps.opsForValue()::get);
    }

    @Override
    public Mono<Boolean> save(String key, Yanki yanki) {
        return  yankiOpps.opsForValue().set(key, yanki);
    }

    @Override
    public Mono<Yanki> findByKey(String key) {
        return yankiOpps.opsForValue().get(key);
    }

    @Override
    public Mono<Boolean> delete(String key) {
        return yankiOpps.opsForValue().delete(key);
    }
    
}
