package com.simplecrud.backend.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simplecrud.backend.api.exception.ResourceNotFoundException;
import com.simplecrud.backend.api.daorepo.CustomerRepository;
import com.simplecrud.backend.api.model.Customer;

@RestController @CrossOrigin(origins = "*")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
    	
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId)
        throws ResourceNotFoundException {
    	
    	Customer customer = customerRepository.findById(customerId)
          .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
    	
        return ResponseEntity.ok().body(customer);
    }
    
    @PostMapping("/customers")
    public Customer createCustomer(@Valid @RequestBody Customer customer) throws Exception {
    	
    	System.out.println("Create/add action for id: " + customer.getId());
    	
        return customerRepository.save(customer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
         @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException, Exception {
    	
    	Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

    	if (customerDetails == null)
    		throw new Exception("Customer information is empty.");

    	if (customerDetails.getCustomerName() != null 
    			&& customerDetails.getCustomerName().trim() != "")
    		customer.setCustomerName(customerDetails.getCustomerName());
    	
    	if (customerDetails.getEmailAddress() != null 
    			&& customerDetails.getEmailAddress().trim() != "")
    		customer.setEmailAddress(customerDetails.getEmailAddress());
    	
    	if (customerDetails.getHomePhone() != null 
    			&& customerDetails.getHomePhone().trim() != "")
    		customer.setHomePhone(customerDetails.getHomePhone());
    	
    	if (customerDetails.getMobilePhone() != null 
    			&& customerDetails.getMobilePhone().trim() != "")
    		customer.setMobilePhone(customerDetails.getMobilePhone());
    	
    	if (customerDetails.getWorkPhone() != null 
    			&& customerDetails.getWorkPhone().trim() != "")
    		customer.setWorkPhone(customerDetails.getWorkPhone());
    	
    	if (customerDetails.getAddress() != null)
    		customer.setAddress(customerDetails.getAddress());
    	
    	if (customerDetails.getOffices() != null 
    			&& customerDetails.getOffices().size() > 0) {
    		customer.setOffices(customerDetails.getOffices());    		
    	} else {
    		customer.setOffices(null);
    	}
    	
        final Customer updatedCustomer = customerRepository.save(customer);

        return ResponseEntity.ok(updatedCustomer);
    }

	@DeleteMapping("/customers/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId)
         throws ResourceNotFoundException {
		
		Customer customer = customerRepository.findById(customerId)
       .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

		customerRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        
        return response;
    }

//    private Address validatedAddress(Address address) {
//    	
//    	Address checkedAddress = new Address();
//    	
//		return checkedAddress;
//	}


}
