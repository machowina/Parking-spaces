package com.machowina.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class ParkingZone {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String city;

	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private Set<User> owners;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private Set<User> operators;
	
	private Long slots;

}
