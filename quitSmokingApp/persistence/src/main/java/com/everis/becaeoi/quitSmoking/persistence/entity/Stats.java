package com.everis.becaeoi.quitSmoking.persistence.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Stats implements AppEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date quitSmoking;
	private Integer smokesPerDay;
	private Double smokesPackPrice;
	private Integer smokingYears;
	
	@OneToOne(mappedBy="stats", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private User user;
	
	private String timeWithoutSmoking;
	private Integer smokesSaved;
	private Double moneySaved;
	private Double daysSaved;//TODO
	private Integer timeSaved;

}
