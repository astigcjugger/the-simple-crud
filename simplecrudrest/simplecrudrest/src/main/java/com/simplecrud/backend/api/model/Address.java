package com.simplecrud.backend.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "address1", nullable = true)
    private String address1;

    @Column(name = "address2", nullable = true)
    private String address2;
    
    @Column(name = "city", nullable = true)
    private String city;
    
    @Column(name = "state", nullable = true)
    private String state;
 
    @Column(name = "zipcode", nullable = true)
    private String zipCode;
    
    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "customer_address_id", nullable = true)
    private Customer customerAddress;
    
    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "office_address_id", nullable = true)
    private Office officeAddress;
    
    public Address() {
    	
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

	public Customer getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(Customer customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Office getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(Office officeAddress) {
		this.officeAddress = officeAddress;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", address1=" + address1 + ", address2=" + address2 + ", city="
				+ city + ", state=" + state + ", zipCode=" + zipCode + "]";
	}


}
