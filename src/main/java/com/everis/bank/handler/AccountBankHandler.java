package com.everis.bank.handler;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.everis.bank.models.documents.AccountBank;
import com.everis.bank.models.services.AccountBankService;

import reactor.core.publisher.Mono;

@Component
public class AccountBankHandler {
	
	@Autowired
	private AccountBankService accountBankService;
	
	public Mono<ServerResponse> listAccountbank(ServerRequest request){
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(accountBankService.findAll(),AccountBank.class);
	}
	
	
	public Mono<ServerResponse> listAccountBankId(ServerRequest request){
		String id = request.pathVariable("id");
		return accountBankService.findById(id).flatMap(p -> ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(p)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> addAccountBank(ServerRequest request){
		Mono<AccountBank> accountbank = request.bodyToMono(AccountBank.class);
		
		return accountbank.flatMap(p -> {
			if(p.getCreateAt()==null) {
				p.setCreateAt(new Date());
			}
			return accountBankService.save(p);
			}).flatMap(p -> ServerResponse
					.created(URI.create("/api/v2/accountbank/".concat(p.getId())))
					.contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromObject(p)));
	}
	
	
	public Mono<ServerResponse> editAccountBank(ServerRequest request){
		Mono<AccountBank> accountbank = request.bodyToMono(AccountBank.class);
		String id = request.pathVariable("id");
		
		Mono<AccountBank> accountbankDb = accountBankService.findById(id);
		return accountbankDb.zipWith(accountbank, (db, req) ->{
			db.setCategoryname(req.getCategoryname());
			db.setTypeAccountBank(req.getTypeAccountBank());
			return db;
		}).flatMap(p -> ServerResponse.created(URI.create("/api/v2/accountbank/".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(accountBankService.save(p),AccountBank.class))
				.switchIfEmpty(ServerResponse.notFound().build());
			
	}
	
	public Mono<ServerResponse> deleteAccountBank(ServerRequest request){
		String id = request.pathVariable("id");
		Mono<AccountBank> accountbankDb = accountBankService.findById(id);
		
		return accountbankDb.flatMap(p -> accountBankService.delete(p).then(ServerResponse.noContent().build()))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	

}
