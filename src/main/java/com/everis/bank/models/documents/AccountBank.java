package com.everis.bank.models.documents;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "bAccountBank")
@Getter
@Setter
@NoArgsConstructor
public class AccountBank {
	
	@Id
	private String id;
	@NotNull
	private String categoryname;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
	
	@Valid
	private TypeAccountBank typeAccountBank;

	public AccountBank(@NotNull String categoryname, @Valid TypeAccountBank typeAccountBank) {
		super();
		this.categoryname = categoryname;
		this.typeAccountBank = typeAccountBank;
	}

	
	
	
	
	
	
	
	 
	
	

}
