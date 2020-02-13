package com.simplecrud.backend.api.controller;

import java.util.ArrayList;
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
import com.simplecrud.backend.api.model.Office;

@RestController @CrossOrigin(origins = "*")
public class AddressController {
	
	@Autowired
	private AddressRepository addressRepository;

    @GetMapping("/addresses")
    public List<com.simplecrud.backend.api.bean.Address> getAllAddresses() {
    	
    	List<Address> listOfAddresses = addressRepository.findAll();
    	List<com.simplecrud.backend.api.bean.Address> bnAddressListing = new ArrayList<com.simplecrud.backend.api.bean.Address>();
    	listOfAddresses.forEach(address -> {
    		bnAddressListing.add(new com.simplecrud.backend.api.bean.Address(address));
    	});
    	

    	return bnAddressListing;
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<com.simplecrud.backend.api.bean.Address> getAddressById(@PathVariable(value = "id") Long addressId)
        throws ResourceNotFoundException {
    	
    	Address address = addressRepository.findById(addressId)
    	          .orElseThrow(() -> new ResourceNotFoundException("Office not found for this id :: " + addressId));
    	    	
    	com.simplecrud.backend.api.bean.Address bnAddress = new com.simplecrud.backend.api.bean.Address(address);
    	    	
        return ResponseEntity.ok().body(bnAddress);
    }
    
//    @PostMapping("/addresses")
//    public Address createAddress(@Valid @RequestBody Address address) throws Exception {
//    	
//    	System.out.println("Create/add action for id: " + address.getId());
//    	Address newAddress = new Address(address);
//    	newAddress.setOffice(address.getOffice());
//    	newAddress.setCustomer(address.getCustomer());
//    	
//        return addressRepository.save(newAddress);
//    }
//
//	@DeleteMapping("/addresses/{id}")
//    public Map<String, Boolean> deleteAddress(@PathVariable(value = "id") Long addressId)
//         throws ResourceNotFoundException {
//		
//		Address address = addressRepository.findById(addressId)
//       .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addressId));
//
//		address.setOffice(null);
//		address.setCustomer(null);
//		addressRepository.delete(address);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        
//        return response;
//    }
//
//    @PutMapping("/addresses/{id}")
//    public ResponseEntity<Address> updateAddress(@PathVariable(value = "id") Long addressId,
//         @Valid @RequestBody Address addressDetails) throws ResourceNotFoundException, Exception {
//    	
//    	Address address = addressRepository.findById(addressId)
//        .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addressId));
//    	
//    	if (addressDetails == null)
//    		throw new Exception("Address is empty.");
//
//    	if (addressDetails.getAddress1() != null 
//    			&& addressDetails.getAddress1().trim() != "")
//    		address.setAddress1(addressDetails.getAddress1());
//    	
//    	if (addressDetails.getAddress2() != null 
//    			&& addressDetails.getAddress2().trim() != "")
//    		address.setAddress2(addressDetails.getAddress2());
//    	
//    	if (addressDetails.getCity() != null 
//    			&& addressDetails.getCity().trim() != "")
//    		address.setCity(addressDetails.getCity());
//    	
//    	if (addressDetails.getState() != null 
//    			&& addressDetails.getState().trim() != "")
//    		address.setState(addressDetails.getState());
//    	
//    	if (addressDetails.getZipCode() != null 
//    			&& addressDetails.getZipCode().trim() != "")
//    		address.setZipCode(addressDetails.getZipCode());
//    	
//    	if (addressDetails.getCustomer() != null)
//    		address.setCustomer(addressDetails.getCustomer());
//    	
//    	if (addressDetails.getOffice() != null)
//    		address.setOffice(addressDetails.getOffice());
//    	
//        final Address updatedAddress = addressRepository.save(address);
//
//        return ResponseEntity.ok(updatedAddress);
//    }


}
