package com.sales.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.models.Product;
import com.sales.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository pr;
	
	public List<Product> findAll() {
		return (ArrayList<Product>) pr.findAll();
	}
	
	public Product findById(Long pId) {
		return pr.findById(pId).orElseThrow();
	}
	
	public void save(Product p) {
		pr.save(p);
	}
}
	