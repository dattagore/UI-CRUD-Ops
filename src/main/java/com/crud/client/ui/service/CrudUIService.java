package com.crud.client.ui.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crud.client.ui.model.Product;

@Service
public class CrudUIService {
	
	@Value("${crud.rest.api.baseURL}")
	private String crudRestAPIBaseURL;
	
	@Autowired RestTemplate restTemplate;
	
	public String save(Product request) {
		restTemplate.postForEntity(crudRestAPIBaseURL.concat("/"), request, Product.class);
		return "Success";
	}

	public List<Product> listAll() {
		Product [] listProducts = restTemplate.getForEntity(crudRestAPIBaseURL.concat("/all"), Product[].class).getBody();
		return Arrays.asList(listProducts);
	}

	public Product get(long productId) {
		return restTemplate.getForEntity(crudRestAPIBaseURL.concat("/" + productId), Product.class).getBody();
	}

	public String delete(long productId) {
		restTemplate.delete(crudRestAPIBaseURL.concat("/" + productId), Product.class);
		return "success";
	}

	public void edit(Product request) {
		restTemplate.put(crudRestAPIBaseURL.concat("/"), request);
	}	
	
}