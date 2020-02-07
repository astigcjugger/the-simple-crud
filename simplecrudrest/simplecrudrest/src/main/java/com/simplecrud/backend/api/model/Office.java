package com.simplecrud.backend.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Offices")
public class Office {

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
    
    @OneToOne(mappedBy = "officeAddress", fetch = FetchType.LAZY,
    		cascade = CascadeType.ALL)
    private Address address;
    
    @ManyToMany(mappedBy = "offices", fetch = FetchType.LAZY)
    private List<Customer> customers;

    public Office() {
    	
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

	@Override
	public String toString() {
		return "Office [id=" + id + ", officeName=" + officeName + ", mainContact=" + mainContact
				+ ", mainPhone=" + mainPhone + ", supportPhone=" + supportPhone + "]";
	}
 

}
