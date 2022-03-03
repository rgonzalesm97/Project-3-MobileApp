package com.example.BootcampMsMobileApp.service;

import com.example.BootcampMsMobileApp.model.MessageDto;
import com.example.BootcampMsMobileApp.model.Yanki;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface YankiService {
    public Flux<Yanki> findAll();
    public Mono<Yanki> findById(String id);
    public Mono<Yanki> findByPhoneNumber(String phoneNumber);
    public Mono<Yanki> save(Yanki yanki);
    public void delete(String id);
    public Mono<Yanki> sendPayments(Yanki yanki, String phoneNumberTo, Double amount);
    public void recibePayments(MessageDto message);

}
