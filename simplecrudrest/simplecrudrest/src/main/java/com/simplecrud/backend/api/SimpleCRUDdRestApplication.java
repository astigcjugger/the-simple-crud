package com.simplecrud.backend.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.simplecrud.backend.api.daorepo.AddressRepository;
import com.simplecrud.backend.api.daorepo.CustomerRepository;
import com.simplecrud.backend.api.daorepo.OfficeRepository;
import com.simplecrud.backend.api.exception.ResourceNotFoundException;
import com.simplecrud.backend.api.model.Address;
import com.simplecrud.backend.api.model.Customer;
import com.simplecrud.backend.api.model.Office;

@SpringBootApplication
public class SimpleCRUDdRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCRUDdRestApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner dataPopulate(
			CustomerRepository customerRepository,
			OfficeRepository officeRepository,
			AddressRepository addressRepository) {
		
		return args -> {
			
			// Associate all Offices to Addresses
			for (int i = 10000; i < 10005; i++) {
				
				Long officeid = new Long(i);
				Office office = officeRepository.findById(officeid)
						.orElseThrow(
						() -> new ResourceNotFoundException("Office not found for this id : " 
						+ officeid));
				
				Address address = addressRepository.findById(officeid)
						.orElseThrow(
						() -> new ResourceNotFoundException("Office not found for this id : " 
						+ officeid));
						
				office.setAddress(address);
			}
			
			// Add 2 Customers
			Customer shainaCustomer = 
					new Customer("Shaina Magdayao", "shainamagdayao@email.com", 
							"(349) 389-8949", null, "(456) 556-7889");
			
			List<Office> shainaOffices = new ArrayList<Office>();

			shainaOffices.add(officeRepository.findById(new Long(10000))
					.orElseThrow(() -> new ResourceNotFoundException("Office not found for this id : " 
							+ 10000)));
			
			shainaOffices.add(officeRepository.findById(new Long(10001))
					.orElseThrow(() -> new ResourceNotFoundException("Office not found for this id : " 
							+ 10001)));
			
			shainaCustomer.setOffices(shainaOffices);

			shainaCustomer.setAddress(addressRepository.findById(new Long(10005))
					.orElseThrow(() -> new ResourceNotFoundException("Address not found for this id : " 
							+ 10005)));

			Customer angelicaCustomer = 
					new Customer("Angelica Panganiban", "angelicap@email.com", 
							"(788) 152-0155", "(510) 222-3333", "(415) 566-1558");
			
			angelicaCustomer.setAddress(addressRepository.findById(new Long(10006))
					.orElseThrow(() -> new ResourceNotFoundException("Address not found for this id : " 
							+ 10006)));

		};
		
	}

}
