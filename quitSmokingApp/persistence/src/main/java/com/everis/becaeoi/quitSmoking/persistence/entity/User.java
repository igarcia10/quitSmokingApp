package com.everis.becaeoi.quitSmoking.persistence.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User implements AppEntity {
	
	@Id
	private String username;
	private String name;
	private String surname;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	private String email;
	private String password;
	
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Stats stats;

}
