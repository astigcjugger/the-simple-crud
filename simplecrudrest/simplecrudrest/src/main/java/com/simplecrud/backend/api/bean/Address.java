package com.simplecrud.backend.api.bean;

public class Address {
    private Long id;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
    private Customer customer;
    private Office office;

    public Address() {
    	
    }
    
    public Address(com.simplecrud.backend.api.model.Address anAddress) {
    	id = anAddress.getId();
    	address1 = anAddress.getAddress1();
    	address2 = anAddress.getAddress2();
    	city = anAddress.getCity();
    	state = anAddress.getState();
    	zipCode = anAddress.getZipCode();
    }
    
    public Address(Long id, String address1, String address2, String city, String state, String zipCode) {
		super();
		this.id = id;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

    public Address(Long id, String address1, String address2, String city, String state, String zipCode, Customer customer, Office office) {
		super();
		this.id = id;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.customer = customer;
		this.office = office;
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

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", state="
				+ state + ", zipCode=" + zipCode + "]";
	}

}
