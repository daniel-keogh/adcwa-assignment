package com.sales.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sales.models.Customer;
import com.sales.services.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	CustomerService cs;

	@RequestMapping(value = "/showCustomers.html")
	public String getCustomers(Model model) {
		model.addAttribute("customers", cs.getAllCustomers());
		return "showCustomers";
	}

	@RequestMapping(value = "/addCustomer.html", method = RequestMethod.GET)
	public String addCustomerGET(Model model) {
		Customer c = new Customer();
		model.addAttribute("customer", c);
		return "addCustomer";
	}

	@RequestMapping(value = "/addCustomer.html", method = RequestMethod.POST)
	public String addCustomerPOST(@Valid @ModelAttribute("customer") Customer c, BindingResult result) {
		if (result.hasErrors()) {
			return "addCustomer";
		}

		cs.save(c);
		return "redirect:showCustomers.html";
	}
}
