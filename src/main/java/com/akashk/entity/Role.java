package com.akashk.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "role_tab")
public class Role {
	
	@Id
	private String role;
	private String roleDesrciption;

}
