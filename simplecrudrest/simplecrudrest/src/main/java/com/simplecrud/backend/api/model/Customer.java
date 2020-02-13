package com.simplecrud.backend.api.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Customers")
public class Customer {
	
//	private static final long serialVersionUID = -6790693372856798580L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "customername", nullable = false)
    private String customerName;

    @Column(name = "emailaddress", nullable = true)
    private String emailAddress;
    
    @Column(name = "workphone", nullable = true)
    private String workPhone;
    
    @Column(name = "homephone", nullable = true)
    private String homePhone;
 
    @Column(name = "mobilephone", nullable = true)
    private String mobilePhone;

//    @OneToOne(fetch = FetchType.LAZY,
//    		cascade = CascadeType.ALL,
//    		mappedBy = "customer")
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Address customerAddress;  
    
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    @JoinTable(
//    		name = "customers_offices", 
//    		joinColumns = {
//    				@JoinColumn(name = "customer_id", referencedColumnName = "id")},
//    		inverseJoinColumns = {
//    				@JoinColumn(name = "office_id", referencedColumnName = "id")}
//    )

//    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
//    @JoinTable(
//  		name = "customers_offices", 
//  		joinColumns = {
//  				@JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = true) },
//  		inverseJoinColumns = {
//  				@JoinColumn(name = "office_id", referencedColumnName = "id", nullable = true) }
//    )
    @ManyToMany
    @JsonIgnore
    private Set<Office> offices = new HashSet<Office>();
    
    public Customer() {
    	
    }
    
	public Customer(String customerName, String emailAddress, String workPhone, String homePhone,
			String mobilePhone) {

		this.customerName = customerName;
		this.emailAddress = emailAddress;
		this.workPhone = workPhone;
		this.homePhone = homePhone;
		this.mobilePhone = mobilePhone;
	}
	
    public Customer(Customer newCustomer) {

    	this.customerName = newCustomer.getCustomerName();
		this.emailAddress = newCustomer.getEmailAddress();
		this.workPhone = newCustomer.getWorkPhone();
		this.homePhone = newCustomer.getHomePhone();
		this.mobilePhone = newCustomer.getMobilePhone();
    }
    
    public Customer(com.simplecrud.backend.api.bean.Customer aCustomer) {
    	this.customerName = aCustomer.getCustomerName();
		this.emailAddress = aCustomer.getEmailAddress();
		this.workPhone = aCustomer.getWorkPhone();
		this.homePhone = aCustomer.getHomePhone();
		this.mobilePhone = aCustomer.getMobilePhone();
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

	public Address getCustomerAddress() {
		return customerAddress;
	}
	
//	public void setCustomerAddress(Address address) {
//		this.customerAddress = address;
//	}

	public void setCustomerAddress(Address address) {
		
		//this.address = address;
		if (sameAsBefore(address))
			return;
		
		Address oldAddress = this.customerAddress;
		this.customerAddress = address;
		if (oldAddress != null)
			oldAddress.setCustomer(null);
		
		if (address != null)
			address.setCustomer(this);
	}
	
	public boolean sameAsBefore(Address newAddress) {
		
		return customerAddress == null ? newAddress == null : customerAddress.equals(newAddress);
	}

	public Set<Office> getOffices() {
		return new HashSet<Office>(offices);
	}
	
	public void addOffice(Office office) {
		if (offices.contains(office))
			return;
		
		offices.add(office);
//		office.addCustomer(this);
		office.getCustomers().put(customerName, this);
	}
	
	public void removeOffice(Office office) {
		if (!offices.contains(office))
			return;
		
		offices.remove(office);
//		office.removeCustomer(this);
		office.getCustomers().remove(customerName);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerName=" + customerName + ", emailAddress=" + emailAddress
				+ ", workPhone=" + workPhone + ", homePhone=" + homePhone + ", mobilePhone=" + mobilePhone + "]";
	}
 
}
