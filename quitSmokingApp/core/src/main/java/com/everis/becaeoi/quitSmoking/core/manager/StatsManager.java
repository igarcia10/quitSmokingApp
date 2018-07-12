package com.everis.becaeoi.quitSmoking.core.manager;

import java.util.GregorianCalendar;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.becaeoi.quitSmoking.persistence.entity.Stats;
import com.everis.becaeoi.quitSmoking.persistence.repository.StatsRepository;

@Service
public class StatsManager {
	
	@Autowired
	private StatsRepository repository;
	
	private GregorianCalendar gc = new GregorianCalendar();
	private DateTime start = new DateTime(gc);
	private DateTime end = DateTime.now();
	private Days days = Days.daysBetween(start, end);
	
	
	public List<Stats> findAll(){
		return (List<Stats>) repository.findAll();
	}
	
	public Stats save(Stats stats) {
		return repository.save(stats);
	}
	
	public StatsInfo showStatsInfo(String username){
		Stats DBStats = repository.findByUserUsername(username);
		return getStatsInfo(DBStats);
	}
	
	private StatsInfo getStatsInfo(Stats stats) {
		
		StatsInfo statsInfo = new StatsInfo();
		statsInfo.setTimeWithoutSmoking(getTimeWithoutSmoking(stats));
		statsInfo.setSmokesSaved(getSmokesSaved(stats));
		statsInfo.setMoneySaved(getMoveySaved(stats));
		statsInfo.setTimeSaved(getTimeSaved(stats));
		statsInfo.setLifeTimeGained(getLifeTimeGained(stats, statsInfo.getSmokesSaved()));
		
		return statsInfo;
	}
	
	private String getTimeWithoutSmoking(Stats stats) {
		gc.setTime(stats.getQuitSmoking());
		Period period = new Period(start,end);
		
		return period.toString();
	}
	
	private Integer getSmokesSaved(Stats stats) {
		gc.setTime(stats.getQuitSmoking());
		Days days = Days.daysBetween(start, end);
		
		return days.getDays()*stats.getSmokesPerDay();
	}
	
	private Double getMoveySaved(Stats stats) {
		gc.setTime(stats.getQuitSmoking());
		
		return (stats.getSmokesPerDay()*stats.getSmokesPackPrice()/20)*days.getDays();
	}
	
	private Integer getTimeSaved(Stats stats) {
		return stats.getSmokesPerDay()*10*days.getDays();
	}
	
	private Double getLifeTimeGained(Stats stats, Integer smokesSaved) {
		return 0.0076*smokesSaved;
	}
	
}
