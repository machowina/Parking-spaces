package com.machowina.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Car {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotBlank
	private String license;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "driver_id")
	private User driver;

	public Car(String license, User driver) {
		super();
		this.license = license;
		this.driver = driver;
	}
	

}
