package com.example.BootcampMsMobileApp.repository;

import com.example.BootcampMsMobileApp.model.Yanki;

import reactor.core.publisher.Mono;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YankiRepository extends ReactiveMongoRepository<Yanki, String> {
	public Mono<Yanki> findByPhoneNumber(String phoneNumber);
}
