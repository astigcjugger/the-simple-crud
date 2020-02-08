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
import com.simplecrud.backend.api.daorepo.OfficeRepository;
import com.simplecrud.backend.api.model.Office;

@RestController @CrossOrigin(origins = "*")
public class OfficeController {
	
	@Autowired
	private OfficeRepository officeRepository;

    @GetMapping("/offices")
    public List<Office> getAllOffices() {
    	
        return officeRepository.findAll();
    }

    @GetMapping("/offices/{id}")
    public ResponseEntity<Office> getOfficeById(@PathVariable(value = "id") Long officeId)
        throws ResourceNotFoundException {
    	
    	Office office = officeRepository.findById(officeId)
          .orElseThrow(() -> new ResourceNotFoundException("Office not found for this id :: " + officeId));
    	
        return ResponseEntity.ok().body(office);
    }
    
    @PostMapping("/offices")
    public Office createOffice(@Valid @RequestBody Office office) throws Exception {
    	
    	System.out.println("Create/add action for id: " + office.getId());
    	
        return officeRepository.save(office);
    }

    @PutMapping("/offices/{id}")
    public ResponseEntity<Office> updateOffice(@PathVariable(value = "id") Long officeId,
         @Valid @RequestBody Office officeDetails) throws ResourceNotFoundException, Exception {
    	
    	Office office = officeRepository.findById(officeId)
        .orElseThrow(() -> new ResourceNotFoundException("Office not found for this id :: " + officeId));

    	if (officeDetails == null)
    		throw new Exception("Office information is empty.");
    	
    	if (officeDetails.getOfficeName() != null 
    			&& officeDetails.getOfficeName().trim() != "")
    		office.setOfficeName(officeDetails.getOfficeName());
    	
    	if (officeDetails.getMainPhone() != null 
    			&& officeDetails.getMainPhone().trim() != "")
    		office.setMainPhone(officeDetails.getMainPhone());
    	
    	if (officeDetails.getMainPhone() != null 
    			&& officeDetails.getMainPhone().trim() != "")
    		office.setMainPhone(officeDetails.getMainPhone());
    	
    	if (officeDetails.getSupportPhone() != null 
    			&& officeDetails.getSupportPhone().trim() != "")
    		office.setSupportPhone(officeDetails.getSupportPhone());
    	
    	if (officeDetails.getAddress() != null)
    		office.setAddress(officeDetails.getAddress());
    	
    	if (officeDetails.getCustomers() != null 
    			&& officeDetails.getCustomers().size() > 0) {
    		office.setCustomers(officeDetails.getCustomers());    		
    	} else {
    		office.setCustomers(null);
    	}
    	
        final Office updatedOffice = officeRepository.save(office);

        return ResponseEntity.ok(updatedOffice);
    }

	@DeleteMapping("/offices/{id}")
    public Map<String, Boolean> deleteOffice(@PathVariable(value = "id") Long officeId)
         throws ResourceNotFoundException {
		
		Office office = officeRepository.findById(officeId)
       .orElseThrow(() -> new ResourceNotFoundException("Office not found for this id :: " + officeId));

		officeRepository.delete(office);
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
