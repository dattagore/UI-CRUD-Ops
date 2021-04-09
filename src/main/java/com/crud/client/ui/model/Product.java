package com.crud.client.ui.model;

import lombok.Data;

@Data
public class Product {

	public Product() {}
	
	public Product(long productId, String productName, Double productPrice) {
		// TODO Auto-generated constructor stub
	}

	private long productId;
	
	private String productName;
	
	private Double productPrice;
	
}
