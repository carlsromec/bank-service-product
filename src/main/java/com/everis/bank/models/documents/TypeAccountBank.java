package com.everis.bank.models.documents;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Document(collection = "TypeAccountBank")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeAccountBank {
	
	
	@Id
	@NotEmpty
	private String id;
	private String name;
	private int limit;
	private Boolean maintenance;
	public TypeAccountBank(String name, int limt, Boolean maintenance) {
		super();
		this.name = name;
		this.limit = limt;
		this.maintenance = maintenance;
	}
	
	
	
	

	
}
