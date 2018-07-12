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
	
	public Stats findByUserUsername(String username){
		Stats DBStats = repository.findByUserUsername(username);
		
		return updateStats(DBStats);
	}
	
	private Stats updateStats(Stats stats) {
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(stats.getQuitSmoking());
		DateTime start = new DateTime(gc);
		DateTime end = DateTime.now();
		Period period = new Period(start,end);
		
		stats.setTimeWithoutSmoking(period.toString());
		
		Days days = Days.daysBetween(start, end);
		
		stats.setSmokesSaved(days.getDays()*stats.getSmokesPerDay());
		stats.setMoneySaved((stats.getSmokesPerDay()*stats.getSmokesPackPrice()/20)*days.getDays());
		stats.setTimeSaved(stats.getSmokesPerDay()*10*days.getDays());
		if(null==stats.getDaysSaved()) {
			stats.setDaysSaved(0d);
		}
		stats.setDaysSaved(stats.getDaysSaved()+0.0076);
		
		return repository.save(stats);
	}
	
}
