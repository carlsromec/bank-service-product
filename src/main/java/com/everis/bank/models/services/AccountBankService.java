package com.everis.bank.models.services;

import com.everis.bank.models.documents.AccountBank;
import com.everis.bank.models.documents.TypeAccountBank;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountBankService {
	
	public Flux<AccountBank> findAll();
	
	public Mono<AccountBank> findById(String id);
	
	public Mono<AccountBank> save(AccountBank accountBank);
	
	public Mono<Void> delete(AccountBank accountBank);
	
	public Flux<TypeAccountBank> findAllTypeAccountBank();
	
	public Mono<TypeAccountBank> findTypeAccountBankById(String id);
	
	public Mono<TypeAccountBank> saveTypeAccountBank(TypeAccountBank typeAccountBank);

}
