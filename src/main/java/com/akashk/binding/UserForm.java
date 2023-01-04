package com.akashk.binding;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.akashk.entity.Role;

import lombok.Data;

@Data
public class UserForm {
	
	private String firstName;
	private String lastName;
	private String email;
	private long mobileNo;
	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date dob;
	private String gender;
	private String country;
	private String state;
	private String city;
	private Set<Role> roles;
}
