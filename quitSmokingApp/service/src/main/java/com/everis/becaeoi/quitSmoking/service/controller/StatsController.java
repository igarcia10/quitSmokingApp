package com.everis.becaeoi.quitSmoking.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.becaeoi.quitSmoking.core.manager.StatsManager;
import com.everis.becaeoi.quitSmoking.persistence.entity.Stats;
import com.everis.becaeoi.quitSmoking.service.DTO.StatsDTO;
import com.everis.becaeoi.quitSmoking.service.DTO.StatsInfoDTO;

@RestController
@RequestMapping("/stats")
public class StatsController {

	@Autowired
	private StatsManager manager;

	@Autowired
	private DozerBeanMapper mapper;

	@GetMapping
	public List<StatsDTO> findAll() {
		List<Stats> statsList = manager.findAll();
		List<StatsDTO> statsDTOList = new ArrayList<>();
		if (null != statsList) {
			for (Stats stats : statsList) {
				statsDTOList.add(mapper.map(stats, StatsDTO.class));
			}
		}
		return statsDTOList;
	}

	@GetMapping("/{username}/statistics")
	public StatsInfoDTO showStatsInfo(@PathVariable("username") String username) {
		return mapper.map(manager.showStatsInfo(username), StatsInfoDTO.class);
	}

	@PostMapping
	public StatsDTO save(@RequestBody StatsDTO dto) {
		return mapper.map(manager.save(mapper.map(dto, Stats.class)), StatsDTO.class);
	}

}
