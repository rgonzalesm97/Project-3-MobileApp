package com.example.BootcampMsMobileApp.service;

import com.example.BootcampMsMobileApp.model.Yanki;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface YankiService {
    public Flux<Yanki> findAll();
    public Mono<Boolean> save(String key, Yanki yanki);
    public Mono<Yanki> findByKey(String key);
    public Mono<Boolean> delete(String key);
    public boolean sendPayments(Yanki yanki);
    public void recibePayments();

}
