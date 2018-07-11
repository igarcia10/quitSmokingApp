package com.everis.becaeoi.quitSmoking.core.manager;

import java.util.List;

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
	
	public Stats findByUserUsername(String username){
		return repository.findByUserUsername(username);
	}
	
	public Stats save(Stats stats) {
		return repository.save(stats);
	}
	
	//TODO business logic
}
