package com.simplecrud.backend.api.bean;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.simplecrud.backend.api.bean.Address;
import com.simplecrud.backend.api.bean.Office;

public class Customer {
	
    private Long id;
    private String customerName;
    private String emailAddress;
    private String workPhone;
    private String homePhone;
    private String mobilePhone;
    private Address address;  
    private ArrayList<Office> offices = new ArrayList<Office>();

    public Customer() {
    	
    }
    
    public Customer(com.simplecrud.backend.api.model.Customer aCustomer) {
    	id = aCustomer.getId();
    	customerName = aCustomer.getCustomerName();
    	emailAddress = aCustomer.getEmailAddress();
    	workPhone = aCustomer.getWorkPhone();
    	homePhone = aCustomer.getHomePhone();
    	mobilePhone = aCustomer.getMobilePhone();
    	address = new Address(aCustomer.getCustomerAddress());
    	
    	Set<com.simplecrud.backend.api.model.Office> theOffices = aCustomer.getOffices();
    	theOffices.forEach(theOffice -> {
    		Office anOffice = new Office(theOffice);
    		offices.add(anOffice);
    	});
    	
    }
    
	public Customer(Long id, String customerName, String emailAddress, String workPhone, String homePhone,
			String mobilePhone, Address customerAddress, ArrayList<Office> offices) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.emailAddress = emailAddress;
		this.workPhone = workPhone;
		this.homePhone = homePhone;
		this.mobilePhone = mobilePhone;
		this.address = customerAddress;
		this.offices = offices;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	
	public String getHomePhone() {
		return homePhone;
	}
	
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address customerAddress) {
		this.address = customerAddress;
	}

	public ArrayList<Office> getOffices() {
		return offices;
	}

	public void setOffices(ArrayList<Office> offices) {
		this.offices = offices;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerName=" + customerName + ", emailAddress=" + emailAddress
				+ ", workPhone=" + workPhone + ", homePhone=" + homePhone + ", mobilePhone=" + mobilePhone
				+ ", address=" + address + ", offices=" + offices + "]";
	}
	
}
