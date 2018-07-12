package com.everis.becaeoi.quitSmoking.service.DTO;

import java.util.Date;

import com.everis.becaeoi.quitSmoking.persistence.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsDTO {
	
	private Long id;
	private Date quitSmoking;
	private Integer smokesPerDay;
	private Double smokesPackPrice;
	private Integer smokingYears;
	private User user;
	private String timeWithoutSmoking;
	private Integer smokesSaved;
	private Double moneySaved;
	private Double daysSaved;
	private Integer timeSaved;

}
