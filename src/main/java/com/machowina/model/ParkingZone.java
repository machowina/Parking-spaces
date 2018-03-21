package com.machowina.model;

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
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
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
	@JoinColumn(name = "owner_zone_id")
	private Set<User> owners;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "operator_zone_id")
	private Set<User> operators;
	
	private Long slots;

	
	public ParkingZone(String city, String name) {
		super();
		this.city = city;
		this.name = name;
	}

	
}
