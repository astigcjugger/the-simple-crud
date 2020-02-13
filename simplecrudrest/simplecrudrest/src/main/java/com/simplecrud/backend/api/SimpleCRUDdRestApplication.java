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
			
			// Create new Offices and Address.
//			Address address1 = new Address("1234 Main St", null, "San Francisco", "CA", "94111");
//			Address address01 = addressRepository.save(address1);
//			
//			Address address2 = new Address("39948 Amazon Road", null, "San Mateo", "CA", "93875");
//			Address address02 = addressRepository.save(address2);
//
//			Address address3 = new Address("579 Hesperian Ave", "Suite 30", "Hayward", "CA", "95623-3221");
//			Address address03 = addressRepository.save(address3);
//
//			Address address4 = new Address("8939 Macy Way", "Room 389", "Fremont", "CO", "78532");
//			Address address04 = addressRepository.save(address4);
//
//			Address address5 = new Address("28839 First St", null, "Los Angeles", "CA", "62253");
//			Address address05 = addressRepository.save(address5);
//
//			Address address6 = new Address("3700 Cologne Pl", null, "Alameda", "UT", "56830");
//			Address address06 = addressRepository.save(address6);

//			Address address7 = new Address("", null, "", "", "");
//			Address address07 = addressRepository.save(address7);
//
//			Address address8 = new Address("", null, "", "", "");
//			Address address08 = addressRepository.save(address8);
//
//			Address address9 = new Address("", null, "", "", "");
//			Address address09 = addressRepository.save(address9);
//
//			Address address10 = new Address("", null, "", "", "");
//			Address address010 = addressRepository.save(address10);
			
			addressRepository.deleteAllInBatch();
			officeRepository.deleteAllInBatch();
			customerRepository.deleteAllInBatch();
			
			Office office7 = new Office("ABC Tower", "Emily Blunt", "(349) 389-8949", "(349) 389-8949");
			office7.setOfficeAddress(new Address("1234 Main St", null, "San Francisco", "CA", "94111"));
			Office office07 = officeRepository.save(office7);

			Office office8 = new Office("Nevada Plaza", "Richard Gere", "(540) 556-1895", "(569) 486-6898");
			office8.setOfficeAddress(new Address("39948 Amazon Road", null, "San Mateo", "CA", "93875"));
			Office office08 = officeRepository.save(office8);

			Office office9 = new Office("Osborne Bldg", "John Lloyd Cruz", "(587) 566-8798", "(866) 488-8888");
			office9.setOfficeAddress(new Address("579 Hesperian Ave", "Suite 30", "Hayward", "CA", "95623-3221"));
			Office office09 = officeRepository.save(office9);

			Office office10 = new Office("Trafalgar Ctr", "Bea Alonzo", "(898) 612-7558", "(888) 153-8995");
			office10.setOfficeAddress(new Address("8939 Macy Way", "Room 389", "Fremont", "CO", "78532"));
			Office office010 = officeRepository.save(office10);
			
			
			// Add 2 Customers
			
			Customer shainaCustomer = 
					new Customer("Shaina Magdayao", "shainamagdayao@email.com", 
							"(349) 389-8949", null, "(456) 556-7889");

			Address shainaAddress = new Address("28839 First St", null, "Los Angeles", "CA", "62253");
			shainaCustomer.setCustomerAddress(shainaAddress);
			shainaCustomer.addOffice(office09);
			shainaCustomer.addOffice(office010);
			customerRepository.save(shainaCustomer);
			
			Customer angelicaCustomer = 
					new Customer("Angelica Panganiban", "angelicap@email.com", 
							"(788) 152-0155", "(510) 222-3333", "(415) 566-1558");
			
			Address angelicaAddress = new Address("3700 Cologne Pl", null, "Alameda", "UT", "56830");
			angelicaCustomer.setCustomerAddress(angelicaAddress);
			angelicaAddress.setCustomer(angelicaCustomer);;
			customerRepository.save(angelicaCustomer);
			

		};
		
	}

}
