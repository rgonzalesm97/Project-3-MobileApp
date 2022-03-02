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
  @GetMapping("/{key}")
  public Mono<Yanki> findByKey(@PathVariable("key") String key) {
      return service.findByKey(key);
  }
  @PostMapping("/{key}")
    public Mono<Boolean> save(@PathVariable("key") String key, @RequestBody Yanki yanki) {
        return  service.save(key, yanki);
       
    }
  @DeleteMapping("/{key}")
    public Mono<Boolean> delete(String key) {
        return service.delete(key);
    }

  

}