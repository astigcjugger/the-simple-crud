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
    public ResponseEntity<com.simplecrud.backend.api.bean.Customer> getCustomerById(@PathVariable(value = "id") Long customerId)
        throws ResourceNotFoundException {
    	
    	Customer customer = customerRepository.findById(customerId)
          .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
    	
    	com.simplecrud.backend.api.bean.Customer bnCustomer = new com.simplecrud.backend.api.bean.Customer(customer);    	

    	System.out.println("Customer address: " + bnCustomer.getAddress());
    	System.out.println("Customer office(s): " + bnCustomer.getOffices());
    	
        return ResponseEntity.ok().body(bnCustomer);
    }
    
    @PostMapping("/customers")
    public com.simplecrud.backend.api.bean.Customer createCustomer(@Valid @RequestBody com.simplecrud.backend.api.bean.Customer customer) throws Exception {
    	
    	System.out.println("Create/add action for id: " + customer.getId());
    	Customer newCustomer = new Customer(customer);
    	newCustomer.setCustomerAddress(new Address(customer.getAddress()));    		    		

//    	Set<Office> tmpCDOffices = newCustomer.getOffices();    	
    	customer.getOffices().forEach(bnOffice -> {
    		newCustomer.addOffice(officeRepository.findById(bnOffice.getId()).orElse(null));
    	});
    	
    	Customer updatedCustomer = customerRepository.save(newCustomer);
    	com.simplecrud.backend.api.bean.Customer retCustomer = new com.simplecrud.backend.api.bean.Customer(updatedCustomer);
    	
        return retCustomer;
    }

	@DeleteMapping("/customers/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId)
         throws ResourceNotFoundException {
		
		Customer customer = customerRepository.findById(customerId)
       .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

    	Set<Office> tmpOffices = customer.getOffices();
    	tmpOffices.forEach(tmpOffice -> {
//    		tmpOffice.removeCustomer(customer);
    		customer.removeOffice(tmpOffice);
    	});
    	
    	Long addressId = customer.getCustomerAddress().getId();    	
    	customer.setCustomerAddress(null);
		customerRepository.delete(customer);
		
		addressRepository.delete(addressRepository.findById(addressId).orElse(null));
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        
        return response;
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<com.simplecrud.backend.api.bean.Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
         @Valid @RequestBody com.simplecrud.backend.api.bean.Customer customerDetails) throws ResourceNotFoundException, Exception {
    	
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
    	
    	Address currAddress = customer.getCustomerAddress();
    	if (currAddress == null) {
    		customer.setCustomerAddress(customerDetails.getAddress() == null ? null : new Address(customerDetails.getAddress()));    		
    	} else {
    		com.simplecrud.backend.api.bean.Address detAddress = customerDetails.getAddress();
    		currAddress.setAddress1(detAddress.getAddress1());
    		currAddress.setAddress2(detAddress.getAddress2());
    		currAddress.setCity(detAddress.getCity());
    		currAddress.setState(detAddress.getState());
    		currAddress.setZipCode(detAddress.getZipCode());
    		addressRepository.save(currAddress);
    	}
    	
    	Set<Office> custOffices = customer.getOffices();
    	custOffices.forEach(currOffice -> {
    		customer.removeOffice(currOffice);
    	});
    	
//    	Set<Office> custOffices = customer.getOffices();
//		custOffices.forEach(currentOffice -> {
//    		currentOffice.removeCustomer(customer);
//    	});
		
    	customerDetails.getOffices().forEach(bnOffice -> {
    		customer.addOffice(officeRepository.findById(bnOffice.getId()).orElse(null));
    	});

    	
        final Customer updatedCustomer = customerRepository.save(customer);
    	com.simplecrud.backend.api.bean.Customer retCustomer = new com.simplecrud.backend.api.bean.Customer(updatedCustomer);

        return ResponseEntity.ok(retCustomer);
    }

}
