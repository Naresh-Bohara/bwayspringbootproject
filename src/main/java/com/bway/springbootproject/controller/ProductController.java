package com.bway.springbootproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bway.springbootproject.model.Product;
import com.bway.springbootproject.repository.ProductRepository;

@RestController
public class ProductController {
	@Autowired
	private ProductRepository prodRepo;
	
	@GetMapping("/api/products")
	public String loadProduct() {
		RestTemplate temp = new RestTemplate();
		Product[] prodsList = temp.getForObject("https://fakestoreapi.com/products", Product[].class);
		prodRepo.saveAll(List.of(prodsList));
		return "product added successfully!";
	}
}
