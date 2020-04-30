package com.sales.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.exceptions.NonExistentEntityException;
import com.sales.exceptions.QuantityTooLargeException;
import com.sales.models.Customer;
import com.sales.models.Order;
import com.sales.models.OrderForm;
import com.sales.models.Product;
import com.sales.repositories.OrderRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository or;
	
	@Autowired
	CustomerService cs;

	@Autowired
	ProductService ps;
	
	public List<Order> findAll() {
		return (ArrayList<Order>) or.findAll();
	}
	
	public void addNewOrder(OrderForm of) throws QuantityTooLargeException, NonExistentEntityException {
		Product p;
		Customer c;
		
		try {
			// Get the Product & Customer by their IDs
			c = cs.findById(of.getcId());
			p = ps.findById(of.getpId());
		} catch (NoSuchElementException e) {
			// Ordered customer and/or product doesn't exist
			throw new NonExistentEntityException(String.format("Customer: %d and/or Product: %d does not exist", 
					of.getcId(), of.getpId()), e);
		}

		// Create the Order
		Order order = new Order();
		order.setCust(c);
		order.setProd(p);
		order.setQty(of.getQty());
		order.setOrderDate(getTodaysDate()); // Set the order date
		
		// Check the Product's remaining quantity
		int remainingQty = p.getQtyInStock() - order.getQty();
		
		if (remainingQty < 0) {
			throw new QuantityTooLargeException("Quantity too large: Product stock = " + p.getQtyInStock());
		}
		
		// Update Product qtyInStock
		p.setQtyInStock(remainingQty);
		
		// Save the order
		or.save(order);
	}
	
	private String getTodaysDate() {
		// Get today's date in yyyy-MM-dd
		// Reference: https://stackoverflow.com/a/31138689
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(new Date());
	}
}
