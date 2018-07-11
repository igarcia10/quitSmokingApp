package com.everis.becaeoi.quitSmoking.service.DTO;

import java.util.Date;

import com.everis.becaeoi.quitSmoking.persistence.entity.Stats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	
	private String username;
	private String name;
	private String surname;
	private Date birthDate;
	private String email;
	private String password;
	private Stats stats;

}
