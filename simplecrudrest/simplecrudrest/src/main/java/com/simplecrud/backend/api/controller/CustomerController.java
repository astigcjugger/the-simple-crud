package com.simplecrud.backend.api.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.simplecrud.backend.api.daorepo.AddressRepository;
import com.simplecrud.backend.api.daorepo.CustomerRepository;
import com.simplecrud.backend.api.daorepo.OfficeRepository;
import com.simplecrud.backend.api.model.Address;
import com.simplecrud.backend.api.model.Customer;
import com.simplecrud.backend.api.model.Office;

@RestController @CrossOrigin(origins = "*")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OfficeRepository officeRepository;
	@Autowired
	private AddressRepository addressRepository;

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
    	Customer newCustomer = new Customer(customer);
    	
    	if (customer.getCustomerAddress() == null) {
        	newCustomer.setCustomerAddress(customer.getCustomerAddress());    		    		
    	} else {
    		
    		Address newAddress = addressRepository.save(customer.getCustomerAddress());
    		newCustomer.setCustomerAddress(newAddress);
    	}
    	
    	Set<Office> tmpCDOffices = customer.getOffices();    	
    	tmpCDOffices.forEach(sentOffice -> {
			Office anOffice = officeRepository.findById(sentOffice.getId()).orElse(null);
			if (anOffice != null)
				anOffice.addCustomer(newCustomer);    		
    	});
    	
        return customerRepository.save(newCustomer);
    }

	@DeleteMapping("/customers/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId)
         throws ResourceNotFoundException {
		
		Customer customer = customerRepository.findById(customerId)
       .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

    	Set<Office> tmpOffices = customer.getOffices();
    	tmpOffices.forEach(tmpOffice -> {
    		tmpOffice.removeCustomer(customer);
    	});
    	
    	customer.setCustomerAddress(null);
		customerRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        
        return response;
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
    	
    	customer.setEmailAddress(customerDetails.getEmailAddress());
    	customer.setHomePhone(customerDetails.getHomePhone());
    	customer.setMobilePhone(customerDetails.getMobilePhone());
    	customer.setWorkPhone(customerDetails.getWorkPhone());    	
    	
		if (!customer.sameAsBefore(customerDetails.getCustomerAddress())) {
	    	if (customerDetails.getCustomerAddress() == null) {
	    		customer.setCustomerAddress(customer.getCustomerAddress());    		    		
	    	} else {
	    		
	    		Address newAddress = addressRepository.save(customerDetails.getCustomerAddress());
	    		customer.setCustomerAddress(newAddress);
	    	}			
		}
    	
    	Set<Office> custOffices = customer.getOffices();
    	if (!custOffices.containsAll(customerDetails.getOffices())) {
        	
    		custOffices.forEach(currentOffice -> {
        		currentOffice.removeCustomer(customer);
        	});
        	
        	Set<Office> tmpCDOffices = customerDetails.getOffices();    	
        	tmpCDOffices.forEach(sentOffice -> {
    			Office anOffice = officeRepository.findById(sentOffice.getId()).orElse(null);
    			if (anOffice != null)
    				anOffice.addCustomer(customer);    		
        	});
    	}
    	    	
        final Customer updatedCustomer = customerRepository.save(customer);

        return ResponseEntity.ok(updatedCustomer);
    }

}
