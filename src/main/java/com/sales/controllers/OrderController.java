package com.sales.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sales.exceptions.QuantityTooLargeException;
import com.sales.models.Order;
import com.sales.services.CustomerService;
import com.sales.services.OrderService;
import com.sales.services.ProductService;

@Controller
public class OrderController {
	@Autowired
	OrderService os;

	@Autowired
	CustomerService cs;

	@Autowired
	ProductService ps;

	@RequestMapping(value = "/showOrders.html")
	public String getOrders(Model model) {
		model.addAttribute("orders", os.getAllOrders());
		return "showOrders";
	}

	@RequestMapping(value = "/newOrder.html", method = RequestMethod.GET)
	public String newOrderGET(Model model) {
		Map<Long, String> customerMap = new LinkedHashMap<>();
		cs.getAllCustomers().forEach(c -> customerMap.put(c.getcId(), c.getcName()));

		Map<Long, String> productMap = new LinkedHashMap<>();
		ps.getAllProducts().forEach(p -> productMap.put(p.getpId(), p.getpDesc()));

		model.addAttribute("custList", customerMap);
		model.addAttribute("prodList", productMap);

		Order o = new Order();
		model.addAttribute("order", o);
		return "newOrder";
	}

	@RequestMapping(value = "/newOrder.html", method = RequestMethod.POST)
	public String newOrderPOST(@Valid @ModelAttribute("order") Order o, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "newOrder";
		}

		try {
			os.addNewOrder(o);
		} catch (QuantityTooLargeException e) {
			model.addAttribute("error", e);
			return "orderError";
		}
		
		return "redirect:showOrders.html";
	}
}
