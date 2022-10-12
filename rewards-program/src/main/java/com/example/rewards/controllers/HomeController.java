package com.example.rewards.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rewards.models.Customer;
import com.example.rewards.services.CustomerService;

@RestController
public class HomeController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }
	
	@GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getOneCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.findOneCustomerById(id));
    }
	
	@GetMapping("/customers/point/total/{id}")
    public ResponseEntity<Customer> getOneCustomerPoint(@PathVariable Long id) {
    	customerService.calculateTotalCustomerPoint(customerService.findOneCustomerById(id));
    	return ResponseEntity.ok(customerService.findOneCustomerById(id));
    }
	
	@GetMapping("/customers/point/month/{id}")
    public ResponseEntity<Customer> getPointPerMonth(@PathVariable Long id) {
    	customerService.calculateCustomerPointPerMonth(customerService.findOneCustomerById(id));
    	return ResponseEntity.ok(customerService.findOneCustomerById(id));
    }
	
	
	@PostMapping("/customers/create")
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer,HttpStatus.OK);
    }
	
	@DeleteMapping("/customers/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
    	customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
	}
    

}
