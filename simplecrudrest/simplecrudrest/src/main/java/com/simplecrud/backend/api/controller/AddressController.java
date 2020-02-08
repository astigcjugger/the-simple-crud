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
import com.simplecrud.backend.api.daorepo.AddressRepository;
import com.simplecrud.backend.api.model.Address;

@RestController @CrossOrigin(origins = "*")
public class AddressController {
	
	@Autowired
	private AddressRepository addressRepository;

    @GetMapping("/addresses")
    public List<Address> getAllAddresses() {
    	
        return addressRepository.findAll();
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable(value = "id") Long addressId)
        throws ResourceNotFoundException {
    	
    	Address address = addressRepository.findById(addressId)
          .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addressId));
    	
        return ResponseEntity.ok().body(address);
    }
    
    @PostMapping("/addresses")
    public Address createAddress(@Valid @RequestBody Address address) throws Exception {
    	
    	System.out.println("Create/add action for id: " + address.getId());
    	
        return addressRepository.save(address);
    }

    @PutMapping("/addresses/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable(value = "id") Long addressId,
         @Valid @RequestBody Address addressDetails) throws ResourceNotFoundException, Exception {
    	
    	Address address = addressRepository.findById(addressId)
        .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addressId));
    	
    	if (addressDetails == null)
    		throw new Exception("Address is empty.");

    	if (addressDetails.getAddress1() != null 
    			&& addressDetails.getAddress1().trim() != "")
    		address.setAddress1(addressDetails.getAddress1());
    	
    	if (addressDetails.getAddress2() != null 
    			&& addressDetails.getAddress2().trim() != "")
    		address.setAddress2(addressDetails.getAddress2());
    	
    	if (addressDetails.getCity() != null 
    			&& addressDetails.getCity().trim() != "")
    		address.setCity(addressDetails.getCity());
    	
    	if (addressDetails.getState() != null 
    			&& addressDetails.getState().trim() != "")
    		address.setState(addressDetails.getState());
    	
    	if (addressDetails.getZipCode() != null 
    			&& addressDetails.getZipCode().trim() != "")
    		address.setZipCode(addressDetails.getZipCode());
    	
    	if (addressDetails.getCustomerAddress() != null)
    		address.setCustomerAddress(addressDetails.getCustomerAddress());
    	
    	if (addressDetails.getOfficeAddress() != null)
    		address.setOfficeAddress(addressDetails.getOfficeAddress());
    	
        final Address updatedAddress = addressRepository.save(address);

        return ResponseEntity.ok(updatedAddress);
    }

	@DeleteMapping("/addresses/{id}")
    public Map<String, Boolean> deleteAddress(@PathVariable(value = "id") Long addressId)
         throws ResourceNotFoundException {
		
		Address address = addressRepository.findById(addressId)
       .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addressId));

		addressRepository.delete(address);
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
