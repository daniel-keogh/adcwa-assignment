package com.sales.controllers;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sales.exceptions.NonExistentEntityException;
import com.sales.exceptions.QuantityTooLargeException;
import com.sales.models.Order;
import com.sales.services.CustomerService;
import com.sales.services.OrderService;
import com.sales.services.ProductService;

@Controller
@SessionAttributes({"custList", "prodList"})
public class OrderController {
	@Autowired
	private OrderService os;

	@Autowired
	private CustomerService cs;

	@Autowired
	private ProductService ps;

	@RequestMapping(value = "/showOrders.html")
	public String getOrders(Model model) {
		model.addAttribute("orders", os.getAllOrders());
		return "showOrders";
	}

	@RequestMapping(value = "/newOrder.html", method = RequestMethod.GET)
	public String newOrderGET(Model model) {
		Map<Long, String> customerMap = new LinkedHashMap<>();
		Map<Long, String> productMap = new LinkedHashMap<>();
		
		cs.getAllCustomers().forEach(c -> customerMap.put(c.getcId(), c.getcName()));
		ps.getAllProducts().forEach(p -> productMap.put(p.getpId(), p.getpDesc()));

		model.addAttribute("custList", customerMap);
		model.addAttribute("prodList", productMap);

		model.addAttribute("order", new Order());

		return "newOrder";
	}

	@RequestMapping(value = "/newOrder.html", method = RequestMethod.POST)
	public String newOrderPOST(@Valid @ModelAttribute("order") Order o, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "newOrder";
		}
		
		try {
			os.addNewOrder(o);
		} catch (QuantityTooLargeException | NonExistentEntityException e) {
			model.addAttribute("error", e);
			return "orderError";
		}
		
		return "redirect:showOrders.html";
	}
}
