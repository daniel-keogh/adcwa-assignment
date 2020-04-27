package com.sales.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.models.Customer;
import com.sales.repositories.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository cr;
	
	public List<Customer> findAll() {
		return (ArrayList<Customer>) cr.findAll();
	}
	
	public Customer findById(Long cId) {
		return cr.findById(cId).orElseThrow();
	}
	
	public void save(Customer c) {
		cr.save(c);
	}
}
