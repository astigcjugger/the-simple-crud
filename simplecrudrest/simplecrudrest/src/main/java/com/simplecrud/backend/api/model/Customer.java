package com.simplecrud.backend.api.model;

import java.util.List;

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

@Entity
@Table(name = "Customers")
public class Customer {
	
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

    @OneToOne(mappedBy = "customerAddress", fetch = FetchType.LAZY, 
    		cascade = CascadeType.ALL)
    private Address address;  
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "customers_offices", 
    		joinColumns = {
    				@JoinColumn(name = "customer_id", referencedColumnName = "id",
    					nullable = false, updatable = false)},
    		inverseJoinColumns = {
    				@JoinColumn(name = "office_id", referencedColumnName = "id",
    					nullable = false, updatable = false)}
    )
    private List<Office> offices;
    
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

	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerName=" + customerName + ", emailAddress=" + emailAddress
				+ ", workPhone=" + workPhone + ", homePhone=" + homePhone + ", mobilePhone=" + mobilePhone + "]";
	}
 
}
