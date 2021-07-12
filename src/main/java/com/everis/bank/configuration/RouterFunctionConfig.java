package com.everis.bank.configuration;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import com.everis.bank.handler.AccountBankHandler;



@Configuration
public class RouterFunctionConfig {
	
	
	@Bean
	public RouterFunction<ServerResponse> routes(AccountBankHandler handler){
		return route(GET("/api/v2/accountbank"), handler::listAccountbank)
				.andRoute(GET("/api/v2/accountbank/{id}"), handler::listAccountBankId)
				.andRoute(POST("/api/v2/accountbank"), handler::addAccountBank)
				.andRoute(PUT("/api/v2/accountbank/{id}"), handler::editAccountBank)
				.andRoute(DELETE("/api/v2/accountbank/{id}"), handler::deleteAccountBank);
				
	}	

}
