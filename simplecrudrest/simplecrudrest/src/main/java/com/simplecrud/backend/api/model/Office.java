package com.simplecrud.backend.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Offices")
public class Office {

//	private static final long serialVersionUID = -8690693372846798580L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "officename", nullable = false)
    private String officeName;

    @Column(name = "maincontact", nullable = true)
    private String mainContact;
    
    @Column(name = "mainphone", nullable = true)
    private String mainPhone;
    
    @Column(name = "supportphone", nullable = true)
    private String supportPhone;
    
//    @OneToOne(fetch = FetchType.LAZY,
//    		cascade = CascadeType.ALL,
//    		mappedBy = "office")
    @OneToOne(mappedBy = "office", cascade = CascadeType.ALL)
    @JsonIgnore
    private Address officeAddress;
    
//xx    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "offices")
//xx    private Set<Customer> customers = new HashSet<Customer>();
    @ManyToMany(mappedBy = "offices", fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @MapKey(name = "customerName")
    @JsonIgnore
    private Map<String, Customer> customers = new HashMap<String, Customer>();
//  private Set<Customer> customers= new HashSet<Customer>();

    public Office() {
    	
    }
    
    public Office(Office newOffice) {
		this.officeName = newOffice.getOfficeName();
		this.mainContact = newOffice.getMainContact();
		this.mainPhone = newOffice.getMainPhone();
		this.supportPhone = newOffice.getSupportPhone();
    }    
    
    public Office(com.simplecrud.backend.api.bean.Office newOffice) {
		this.officeName = newOffice.getOfficeName();
		this.mainContact = newOffice.getMainContact();
		this.mainPhone = newOffice.getMainPhone();
		this.supportPhone = newOffice.getSupportPhone();
    }    
    
	public Office(String officeName, String mainContact, String mainPhone, String supportPhone) {
		this.officeName = officeName;
		this.mainContact = mainContact;
		this.mainPhone = mainPhone;
		this.supportPhone = supportPhone;
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

	public Address getOfficeAddress() {
		return officeAddress;
	}

//	public void setOfficeAddress(Address address) {
//		this.officeAddress = address;
//	}

	public void setOfficeAddress(Address address) {
		//this.address = officeAddress;
		if (sameAsBefore(address))
			return;
		
		Address oldAddress = this.officeAddress;
		this.officeAddress = address;
		if (oldAddress != null)
			oldAddress.setOffice(null);
		
		if (address != null)
			address.setOffice(this);
	}

	public boolean sameAsBefore(Address newAddress) {
		
		return officeAddress == null ? newAddress == null : officeAddress.equals(newAddress);
	}	
	
//	public Set<Customer> getCustomers() {
//		return new HashSet<Customer>(customers);
//	}
//
//	public void addCustomer(Customer customer) {
//		if (customers.contains(customer))
//			return;
//		
//		customers.add(customer);
//		customer.getOffices().add(this);
//	}
//	
//	public void removeCustomer(Customer customer) {
//		if (!customers.contains(customer))
//			return;
//		
//		customers.remove(customer);
//		customer.getOffices().remove(this);		
//	}	

	public Map<String, Customer> getCustomers() {
		return new HashMap<String, Customer>(customers);
	}

	public void addCustomer(Customer customer) {
		if (customers.containsValue(customer))
			return;
	
		customers.put(customer.getCustomerName(), customer);
		customer.getOffices().add(this);
	}

	public void removeCustomer(Customer customer) {
		if (!customers.containsValue(customer))
			return;
	
		customers.remove(customer.getCustomerName());
		customer.getOffices().remove(this);		
	}	

	@Override
	public String toString() {
		return "Office [id=" + id + ", officeName=" + officeName + ", mainContact=" + mainContact
				+ ", mainPhone=" + mainPhone + ", supportPhone=" + supportPhone + "]";
	}
 

}
