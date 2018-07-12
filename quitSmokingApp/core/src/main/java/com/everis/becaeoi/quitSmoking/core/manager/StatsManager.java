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
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(stats.getQuitSmoking());
		DateTime start = new DateTime(gc);
		DateTime end = DateTime.now();
		Period period = new Period(start,end);
		Days days = Days.daysBetween(start, end);
		
		statsInfo.setTimeWithoutSmoking(period.toString());
		statsInfo.setSmokesSaved(days.getDays()*stats.getSmokesPerDay());
		statsInfo.setMoneySaved((stats.getSmokesPerDay()*stats.getSmokesPackPrice()/20)*days.getDays());
		statsInfo.setTimeSaved(stats.getSmokesPerDay()*10*days.getDays());
		statsInfo.setLifeTimeGained(0.0076*statsInfo.getSmokesSaved());
		
		return statsInfo;
	}
	
}
