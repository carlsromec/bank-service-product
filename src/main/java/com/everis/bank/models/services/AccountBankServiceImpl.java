package com.everis.bank.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.bank.models.dao.AccountBankDao;
import com.everis.bank.models.dao.TypeAccountBankDao;
import com.everis.bank.models.documents.AccountBank;
import com.everis.bank.models.documents.TypeAccountBank;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountBankServiceImpl implements AccountBankService {

	@Autowired
	private AccountBankDao accountBankDao;
	
	@Autowired
	private TypeAccountBankDao typeAccountBankDao;
	
	@Override
	public Flux<AccountBank> findAll() {
		return accountBankDao.findAll();
	}

	@Override
	public Mono<AccountBank> findById(String id) {
		return accountBankDao.findById(id);
	}

	@Override
	public Mono<AccountBank> save(AccountBank accountBank) {
		return accountBankDao.save(accountBank);
	}

	@Override
	public Mono<Void> delete(AccountBank accountBank) {
		return accountBankDao.delete(accountBank);
	}

	@Override
	public Flux<TypeAccountBank> findAllTypeAccountBank() {
		return typeAccountBankDao.findAll();
	}

	@Override
	public Mono<TypeAccountBank> findTypeAccountBankById(String id) {
		return typeAccountBankDao.findById(id);
	}

	@Override
	public Mono<TypeAccountBank> saveTypeAccountBank(TypeAccountBank typeAccountBank) {
		return typeAccountBankDao.save(typeAccountBank);
	}

}
