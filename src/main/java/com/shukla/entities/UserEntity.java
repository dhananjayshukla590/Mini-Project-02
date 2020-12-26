package com.shukla.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USER_MASTER")
@Data
public class UserEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "USER_FIRSTNAME")
	private String userFirstName;
	
	@Column(name = "USER_LASTNAME")
	private String userLastName;
	
	@Column(name = "USER_EMAIL")
	private String userEmail;
	
	@Column(name = "USER_PHONENUMBER")
	private String userPhoneNumber;
	
	@Column(name = "USER_DOB")
	private String userDob;
	
	@Column(name = "COUNTRY")
	private Integer country;
	
	@Column(name = "STATE")
	private Integer state;
	
	@Column(name = "CITY")
	private Integer city;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "ACCOUNT_STATUS")
	private String accountStatus;
}
