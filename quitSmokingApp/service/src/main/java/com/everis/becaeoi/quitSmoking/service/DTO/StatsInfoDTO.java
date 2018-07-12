package com.everis.becaeoi.quitSmoking.service.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsInfoDTO {
	
	private String timeWithoutSmoking;
	private Integer smokesSaved;
	private Double moneySaved;
	private Double lifeTimeGained;
	private Integer timeSaved;

}
