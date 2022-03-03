package com.example.BootcampMsMobileApp.controller;

import com.example.BootcampMsMobileApp.model.Yanki;
import com.example.BootcampMsMobileApp.service.YankiService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@AllArgsConstructor
@RestController
@RequestMapping("/yanki")
public class YankiController {
	private final YankiService service;


	@GetMapping()
	public Flux<Yanki> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Yanki> findById(@PathVariable("key") String key) {
		return service.findById(key);
	}
	
	@PostMapping
	public Mono<Yanki> save(@RequestBody Yanki yanki) {
		return  service.save(yanki);
	}
	
	@DeleteMapping("/{id}")
    public void delete(@PathVariable("id")String id) {
        service.delete(id);
    }
	
	@PostMapping("sendTo/{phoneNumberTo}/{amount}")
	public Mono<Yanki> sendMony(@PathVariable("phoneNumberTo") String phoneNumberTo,
								@RequestBody Yanki yanki,
								@PathVariable("amount") Double amount){
		return service.sendPayments(yanki, phoneNumberTo, amount);
	}

}