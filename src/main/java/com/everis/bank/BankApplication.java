package com.everis.bank;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.everis.bank.models.documents.AccountBank;
import com.everis.bank.models.documents.TypeAccountBank;
import com.everis.bank.models.services.AccountBankService;

import reactor.core.publisher.Flux;
@EnableEurekaClient
@SpringBootApplication
public class BankApplication implements CommandLineRunner {
	
	@Autowired
	private AccountBankService accountBankService;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(BankApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.dropCollection("bAccountBank").subscribe();
		mongoTemplate.dropCollection("TypeAccountBank").subscribe();
		
		
		TypeAccountBank Ahorro = new TypeAccountBank("Ahorro",1,false);
		TypeAccountBank cuentacorriente = new TypeAccountBank("cuenta corriente",3000,true);
		TypeAccountBank plazofijo = new TypeAccountBank("plazo fijo",1,false);
		
		
		Flux.just(Ahorro, cuentacorriente, plazofijo)
		.flatMap(accountBankService::saveTypeAccountBank)
		.doOnNext(t ->{
			log.info("TypeAccountBank created: " + t.getName() + ", Id: " + t.getId());
		}).thenMany(
				Flux.just(new AccountBank( "cuenta bancaria",Ahorro),
						new AccountBank("cuenta bancaria",cuentacorriente),
						new AccountBank("cuenta bancaria",plazofijo)
						
						)
				.flatMap(accountBank ->{
					accountBank.setCreateAt(new Date());
					return accountBankService.save(accountBank);
				})
		)
		.subscribe(accountBank -> log.info("Insert: " + accountBank.getId()));
		
				
		
	}

}
