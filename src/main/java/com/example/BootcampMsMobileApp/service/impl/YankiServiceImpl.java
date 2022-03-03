
package com.example.BootcampMsMobileApp.service.impl;
import com.example.BootcampMsMobileApp.model.MessageDto;
import com.example.BootcampMsMobileApp.model.Yanki;

import com.example.BootcampMsMobileApp.repository.YankiRepository;
import com.example.BootcampMsMobileApp.service.YankiService;


import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class YankiServiceImpl implements YankiService {
    
	private final KafkaTemplate<String, MessageDto> kafkaTemplate;
	private final YankiRepository repo;

 
    @Override
    public Mono<Yanki> sendPayments(Yanki yanki, String phoneNumberTo, Double amount) {
    	return Mono.just(yanki).flatMap(resp->checkAmount(yanki, amount))
		    					.flatMap(resp-> restAmount(yanki, amount, resp))
		    					.flatMap(resp -> {
		    						if(resp.getId()!=null){
		    							MessageDto message = MessageDto.builder()
		    									.message("pay to")
		    									.phoneNumber(phoneNumberTo)
		    									.amount(amount)
		    									.build();
		    							kafkaTemplate.send("yankiPay", message);
		    							System.out.println("Send message ... " + message);
		    							return repo.save(resp);		    							
		    						}else return Mono.just(resp);
		    					});
    }

    @Override
    @KafkaListener(topics="yankiPay", groupId="groupId")
    public void recibePayments(MessageDto message) {
    	System.out.println("Recive message ... " + message);
        repo.findByPhoneNumber(message.getPhoneNumber()).flatMap(resp->addAmount(resp, message.getAmount()))
        												.flatMap(this::save)
        												.subscribe();
    }

    @Override
    public Flux<Yanki> findAll() {
        return repo.findAll();
    }

    @Override
    public Mono<Yanki> save(Yanki yanki) {
        return  repo.save(yanki);
    }

    @Override
    public Mono<Yanki> findById(String id) {
        return repo.findById(id);
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id).subscribe();
    }

	@Override
	public Mono<Yanki> findByPhoneNumber(String phoneNumber) {
		return repo.findByPhoneNumber(phoneNumber);
	}
	
	//UTIL METHODS
	private Mono<Boolean> checkAmount(Yanki yanki, Double amount) {
		return yanki.getBalance()>amount ? Mono.just(true)
										 : Mono.just(false);
	}
    
	private Mono<Yanki> restAmount(Yanki yanki, Double amount, Boolean exec){
		if(exec) {
			yanki.setBalance(yanki.getBalance()-amount);
			return Mono.just(yanki);
		}else {
			return Mono.error(() -> new IllegalArgumentException("Not enough balance"));
		}
	}
	
	private Mono<Yanki> addAmount(Yanki yanki, Double amount){
		yanki.setBalance(yanki.getBalance()+amount);
		return Mono.just(yanki);
	}

}
