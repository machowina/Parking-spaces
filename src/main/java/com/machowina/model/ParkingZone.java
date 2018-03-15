package com.machowina.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class ParkingZone {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotBlank
	private String city;

	@NotBlank
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private Set<User> owners;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private Set<User> operators;
	
	private Long slots;

	
	public ParkingZone(String city, String name) {
		super();
		this.city = city;
		this.name = name;
	}

	
}
