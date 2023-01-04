package com.akashk.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "state_master")
@Data
public class State {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer stateId;
	private String stateName;
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;
	@OneToMany(mappedBy = "state")
	private Set<City> cities;

}
