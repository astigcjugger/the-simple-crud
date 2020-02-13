package com.simplecrud.backend.api.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Office {

    private Long id;
    private String officeName;
    private String mainContact;
    private String mainPhone;
    private String supportPhone;
    private Address address;
    private List<Customer> customers = new ArrayList<Customer>();

    public Office() {
    	
    }

    public Office(com.simplecrud.backend.api.model.Office anOffice) {
    	id = anOffice.getId();
    	officeName = anOffice.getOfficeName();
    	mainContact = anOffice.getMainContact();
    	mainPhone = anOffice.getMainPhone();
    	supportPhone = anOffice.getSupportPhone();
    	address = new Address(anOffice.getOfficeAddress());

//    	Map<String, com.simplecrud.backend.api.model.Customer> theCustomers = anOffice.getCustomers();
//    	Set<Map.Entry<String, com.simplecrud.backend.api.model.Customer>> customerSet = theCustomers.entrySet();
//    	customerSet.forEach(entry -> {
//    		com.simplecrud.backend.api.model.Customer cust1 = entry.getValue();
//        	customers.add(new Customer(cust1));
//    	});
    }

    public Office(Long id, String officeName, String mainContact, String mainPhone, String supportPhone) {
		this.id = id;
		this.officeName = officeName;
		this.mainContact = mainContact;
		this.mainPhone = mainPhone;
		this.supportPhone = supportPhone;
	}
    
    public Office(Long id, String officeName, String mainContact, String mainPhone, String supportPhone, Address address, List<Customer> customers) {
		this.id = id;
		this.officeName = officeName;
		this.mainContact = mainContact;
		this.mainPhone = mainPhone;
		this.supportPhone = supportPhone;
		this.address = address;
		this.customers.addAll(customers);
	}
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getMainContact() {
		return mainContact;
	}
	public void setMainContact(String mainContact) {
		this.mainContact = mainContact;
	}
	public String getMainPhone() {
		return mainPhone;
	}
	public void setMainPhone(String mainPhone) {
		this.mainPhone = mainPhone;
	}
	public String getSupportPhone() {
		return supportPhone;
	}
	public void setSupportPhone(String supportPhone) {
		this.supportPhone = supportPhone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Office [id=" + id + ", officeName=" + officeName + ", mainContact=" + mainContact + ", mainPhone="
				+ mainPhone + ", supportPhone=" + supportPhone + "]";
	}

}
