package com.simplecrud.backend.api.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Addresses")
public class Address {

//	private static final long serialVersionUID = -4238693372846798580L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "address1", nullable = false)
    private String address1;

    @Column(name = "address2", nullable = true)
    private String address2;
    
    @Column(name = "city", nullable = false)
    private String city;
    
    @Column(name = "state", nullable = false)
    private String state;
 
    @Column(name = "zipcode", nullable = true)
    private String zipCode;
    
//    @OneToOne(fetch = FetchType.LAZY, optional = true)S
//    @JoinColumn(name = "customer_id", nullable = true)
    @OneToOne(optional = true)
    @JoinColumn(name = "customer_id", nullable = true)
    @JsonIgnore
    private Customer customer;
    
//    @OneToOne(fetch = FetchType.LAZY, optional = true)
//    @JoinColumn(name = "office_id", nullable = true)
    @OneToOne(optional = true)
    @JoinColumn(name = "office_id", nullable = true)
    private Office office;
    
    public Address() {
    	
    }
    
    public Address(Address newAddress) {
		this.address1 = newAddress.getAddress1();
		this.address2 = newAddress.getAddress2();
		this.city = newAddress.getCity();
		this.state = newAddress.getState();
		this.zipCode = newAddress.getZipCode();
    }
    
    public Address(com.simplecrud.backend.api.bean.Address newAddress) {
		this.address1 = newAddress.getAddress1();
		this.address2 = newAddress.getAddress2();
		this.city = newAddress.getCity();
		this.state = newAddress.getState();
		this.zipCode = newAddress.getZipCode();
    }
    
	public Address(String address1, String address2, String city, String state, String zipCode) {
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

//	public void setCustomer(Customer customerAddress) {
//		//this.customerAddress = customer;
//		if (sameCustomerBefore(customerAddress))
//			return;
//		
//		Customer oldCustomer = this.customer;
//		this.customer = customerAddress;
//		if (oldCustomer != null) 
//			oldCustomer.setCustomerAddress(null);
//		
//		if (customerAddress != null)
//			customerAddress.setCustomerAddress(this);
//	}
//	
//	private boolean sameCustomerBefore(Customer newCustomer) {
//		if (customer == null) 
//			return newCustomer == null;
//		
//		return customer.equals(newCustomer);
//	}

	public Office getOffice() {
		return office;
	}
	
	public void setOffice(Office office) {
		this.office = office;
	}

//	public void setOffice(Office officeAddress) {
//		//this.officeAddress = office;
//		if (sameOfficeBefore(officeAddress))
//			return;
//		
//		Office oldOffice = this.office;
//		this.office = officeAddress;
//		if (oldOffice != null) 
//			oldOffice.setOfficeAddress(null);
//		
//		if (officeAddress != null)
//			officeAddress.setOfficeAddress(this);
//	}
//
//	private boolean sameOfficeBefore(Office newOffice) {
//		if (office == null) 
//			return newOffice == null;
//		
//		return office.equals(newOffice);
//	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", address1=" + address1 + ", address2=" + address2 + ", city="
				+ city + ", state=" + state + ", zipCode=" + zipCode + "]";
	}


}
