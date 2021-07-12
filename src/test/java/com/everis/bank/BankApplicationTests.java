package com.everis.bank;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.everis.bank.models.documents.AccountBank;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankApplicationTests {
	
	@Autowired
	private WebTestClient webTestClient;

	
	@Test
	public void listAccountBankTest() {
		
		try {
			webTestClient.get()
			.uri("/api/v2/accountbank")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
		    .expectBodyList(AccountBank.class)
		    .consumeWith(response -> {
		    	List<AccountBank> accountBank = response.getResponseBody();
		    	accountBank.forEach(pw -> {
		    		System.out.println(pw.getCategoryname());
		    	});
		    	Assertions.assertThat(accountBank.size()>0).isTrue();
		    });
			
			

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	

}
