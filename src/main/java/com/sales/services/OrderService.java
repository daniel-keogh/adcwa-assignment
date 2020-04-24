package com.sales.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.exceptions.QuantityTooLargeException;
import com.sales.models.Order;
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
	
	public ArrayList<Order> getAllOrders() {
		return (ArrayList<Order>) or.findAll();
	}
	
	public void addNewOrder(Order order) throws QuantityTooLargeException {
		// Set the order date
		// Ref: https://stackoverflow.com/a/31138689
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		order.setOrderDate(dateFormat.format(new Date()));
		
		// Update Product qtyInStock
		Product p = ps.findOne(order.getProd().getpId());
		if (p != null) {
			int remainingQty = p.getQtyInStock() - order.getQty();
			
			if (remainingQty < 0) {
				throw new QuantityTooLargeException("Quantity too Large: Product stock = " + p.getQtyInStock());
			}
			
			p.setQtyInStock(remainingQty);
		}
		
		or.save(order);
	}
}
