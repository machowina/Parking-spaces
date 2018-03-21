package com.machowina.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotBlank
    private String username;
	
	@NotBlank
	private String password;
	
	@ManyToOne
	private Role role;
	
	private String driverType;

	public User(String username, String password, String driverType) {
		super();
		this.username = username;
		this.password = password;
		this.driverType = driverType;
	}
	

}
