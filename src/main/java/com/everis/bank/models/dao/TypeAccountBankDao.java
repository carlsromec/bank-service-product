package com.everis.bank.models.dao;

import com.everis.bank.models.documents.TypeAccountBank;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface TypeAccountBankDao extends ReactiveMongoRepository<TypeAccountBank, String> {

}
