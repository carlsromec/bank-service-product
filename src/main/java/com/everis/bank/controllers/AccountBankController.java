package com.everis.bank.controllers;

import com.everis.bank.models.documents.AccountBank;
import com.everis.bank.models.services.AccountBankService;



import java.net.URI;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/accountbank")
public class AccountBankController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(AccountBankController.class);
	
	@Autowired
	private AccountBankService accountBankService;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<AccountBank>>> listAccountBank(){
		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(accountBankService.findAll())
				);
	}
	
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<AccountBank>> listAccountBankId(@PathVariable String id){
		return accountBankService.findById(id).map(p ->ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());		
	}
	
	
	@PostMapping
	public Mono<ResponseEntity<AccountBank>> createAccountBank(@RequestBody AccountBank accountBank){
		if(accountBank.getCreateAt()== null) {
			accountBank.setCreateAt(new Date());
		}
		
		return accountBankService.save(accountBank).map(p ->ResponseEntity.created(URI.create("/api/accountbank".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(p));
	}
	
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<AccountBank>> editAccountBank(@RequestBody AccountBank accountBank,@PathVariable String id){
		return accountBankService.findById(id).flatMap(p -> {
			p.setCategoryname(accountBank.getCategoryname());
			p.setTypeAccountBank(accountBank.getTypeAccountBank());
			return accountBankService.save(p);
		}).map(p -> ResponseEntity.created(URI.create("/api/accountbank".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(p))
		.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteAccountBank(@PathVariable String id){
		
		return accountBankService.findById(id).flatMap(p ->{
			return accountBankService.delete(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
}
