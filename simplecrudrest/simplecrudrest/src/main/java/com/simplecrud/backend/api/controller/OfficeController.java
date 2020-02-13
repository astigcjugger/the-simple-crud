package com.simplecrud.backend.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

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
import com.simplecrud.backend.api.daorepo.OfficeRepository;
import com.simplecrud.backend.api.model.Address;
import com.simplecrud.backend.api.model.Customer;
import com.simplecrud.backend.api.model.Office;

@RestController @CrossOrigin(origins = "*")
public class OfficeController {
	
	@Autowired
	private OfficeRepository officeRepository;
	@Autowired
	private AddressRepository addressRepository;

    @GetMapping("/offices")
    public List<com.simplecrud.backend.api.bean.Office> getAllOffices() {
    	
    	List<Office> listOfOffices = officeRepository.findAll();
    	List<com.simplecrud.backend.api.bean.Office> bnOfficeListing = new ArrayList<com.simplecrud.backend.api.bean.Office>();
    	listOfOffices.forEach(office -> {
    		bnOfficeListing.add(new com.simplecrud.backend.api.bean.Office(office));
    	});
    	
        return bnOfficeListing;
    }

    @GetMapping("/offices/{id}")
    public ResponseEntity<com.simplecrud.backend.api.bean.Office> getOfficeById(@PathVariable(value = "id") Long officeId)
        throws ResourceNotFoundException {
    	
    	Office office = officeRepository.findById(officeId)
          .orElseThrow(() -> new ResourceNotFoundException("Office not found for this id :: " + officeId));
    	
    	com.simplecrud.backend.api.bean.Office bnOffice = new com.simplecrud.backend.api.bean.Office(office);
    	
        return ResponseEntity.ok().body(bnOffice);
    }
    
//    @PostMapping("/offices")
//    public Office createOffice(@Valid @RequestBody Office office) throws Exception {
//    	
//    	System.out.println("Create/add action for id: " + office.getId());
//    	Office newOffice = new Office(office);
//
//		newOffice.setOfficeAddress(office.getOfficeAddress());    		    		
//
//    	return officeRepository.save(newOffice);
//    }

//	@DeleteMapping("/offices/{id}")
//    public Map<String, Boolean> deleteOffice(@PathVariable(value = "id") Long officeId)
//         throws ResourceNotFoundException {
//		
//		Office office = officeRepository.findById(officeId)
//       .orElseThrow(() -> new ResourceNotFoundException("Office not found for this id :: " + officeId));
//
//		Set<Customer> customers = office.getCustomers();
//		customers.forEach(customer -> {
//			office.removeCustomer(customer);
//		});
//		
//		office.setOfficeAddress(null);
//		officeRepository.delete(office);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        
//        return response;
//    }

//    @PutMapping("/offices/{id}")
//    public ResponseEntity<Office> updateOffice(@PathVariable(value = "id") Long officeId,
//         @Valid @RequestBody Office officeDetails) throws ResourceNotFoundException, Exception {
//    	
//    	Office office = officeRepository.findById(officeId)
//        .orElseThrow(() -> new ResourceNotFoundException("Office not found for this id :: " + officeId));
//
//    	if (officeDetails == null)
//    		throw new Exception("Office information is empty.");
//    	
//    	if (officeDetails.getOfficeName() != null 
//    			&& officeDetails.getOfficeName().trim() != "")
//    		office.setOfficeName(officeDetails.getOfficeName());
//    	
//    	office.setMainPhone(officeDetails.getMainPhone());
//    	office.setMainPhone(officeDetails.getMainPhone());
//    	office.setSupportPhone(officeDetails.getSupportPhone());
//    	    	
//    	office.setOfficeAddress(officeDetails.getOfficeAddress());    		
//    	
//		Set<Customer> tmpCustomers = office.getCustomers();
//		tmpCustomers.forEach(customer -> {
//			office.removeCustomer(customer);
//		});
//		    	
//        final Office updatedOffice = officeRepository.save(office);
//
//        return ResponseEntity.ok(updatedOffice);
//    }

}
