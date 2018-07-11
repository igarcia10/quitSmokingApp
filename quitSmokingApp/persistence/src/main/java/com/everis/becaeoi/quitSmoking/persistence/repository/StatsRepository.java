package com.everis.becaeoi.quitSmoking.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.everis.becaeoi.quitSmoking.persistence.entity.Stats;

public interface StatsRepository extends CrudRepository<Stats, Long>{
	
	public Stats findByUserUsername(String username);
}
