package com.everis.bank.models.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.everis.bank.models.documents.AccountBank;

public interface AccountBankDao extends ReactiveMongoRepository<AccountBank, String> {

}
