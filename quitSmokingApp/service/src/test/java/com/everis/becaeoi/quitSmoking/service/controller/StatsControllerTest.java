package com.everis.becaeoi.quitSmoking.service.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.everis.becaeoi.quitSmoking.core.manager.StatsInfo;
import com.everis.becaeoi.quitSmoking.core.manager.StatsManager;
import com.everis.becaeoi.quitSmoking.persistence.entity.Stats;
import com.everis.becaeoi.quitSmoking.service.DTO.StatsDTO;
import com.everis.becaeoi.quitSmoking.service.DTO.StatsInfoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class StatsControllerTest {

	@InjectMocks
	private StatsController controller;

	@Mock
	private StatsManager manager;

	@Mock
	private DozerBeanMapper dozerMapper;

	private MockMvc mockMvc;

	private ObjectMapper mapper;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		this.mapper = new ObjectMapper();
	}

	@Test
	public void testFindAll() throws Exception {
		// Arrange
		Mockito.when(manager.findAll()).thenReturn(null);
		// Act
		ResultActions perform = mockMvc.perform(get("/stats"));
		// Assert
		perform.andExpect(status().isOk());
	}

	@Test
	public void testFindAllWithContent() throws Exception {
		// Arrange
		Stats stats1 = new Stats();
		stats1.setSmokingYears(10);
		StatsDTO statsDTO1 = new StatsDTO();
		statsDTO1.setSmokingYears(10);
		Stats stats2 = new Stats();
		stats2.setSmokingYears(20);
		StatsDTO statsDTO2 = new StatsDTO();
		statsDTO2.setSmokingYears(20);
		List<Stats> statsList = new ArrayList<>();
		statsList.add(stats1);
		statsList.add(stats2);
		List<StatsDTO> statsDTOList = new ArrayList<>();
		statsDTOList.add(statsDTO1);
		statsDTOList.add(statsDTO2);
		Mockito.when(manager.findAll()).thenReturn(statsList);
		Mockito.when(dozerMapper.map(stats1, StatsDTO.class)).thenReturn(statsDTO1);
		Mockito.when(dozerMapper.map(stats2, StatsDTO.class)).thenReturn(statsDTO2);
		// Act
		ResultActions perform = mockMvc.perform(get("/stats"));
		// Assert
		perform.andExpect(status().isOk());
		perform.andExpect(content().json(mapper.writeValueAsString(statsDTOList)));
	}

	@Test
	public void testShowStatsInfo() throws Exception {
		// Arrange
		StatsInfo statsInfo = new StatsInfo();
		statsInfo.setSmokesSaved(10);
		StatsInfoDTO statsInfoDTO = new StatsInfoDTO();
		statsInfoDTO.setSmokesSaved(10);
		Mockito.when(manager.showStatsInfo("username")).thenReturn(statsInfo);
		Mockito.when(dozerMapper.map(statsInfo, StatsInfoDTO.class)).thenReturn(statsInfoDTO);
		// Act
		ResultActions perform = mockMvc.perform(get("/stats/username/statistics"));
		// Assert
		perform.andExpect(status().isOk());
		perform.andExpect(content().json(mapper.writeValueAsString(statsInfoDTO)));
	}

	@Test
	public void testSave() throws Exception {
		// Arrange
		StatsDTO statsDTO = new StatsDTO();
		Mockito.when(manager.save(null)).thenReturn(null);
		// Act
		ResultActions perform = mockMvc.perform(post("/stats").content(mapper.writeValueAsString(statsDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		// Assert
		perform.andExpect(status().isOk());
	}

	@Test
	public void testSaveWithContent() throws Exception {
		// Arrange
		Stats stats1 = new Stats();
		stats1.setSmokingYears(10);
		StatsDTO statsDTO1 = new StatsDTO();
		statsDTO1.setSmokingYears(10);
		Mockito.when(dozerMapper.map(Mockito.any(StatsDTO.class), Mockito.any())).thenReturn(stats1);
		Mockito.when(manager.save(stats1)).thenReturn(stats1);
		Mockito.when(dozerMapper.map(stats1, StatsDTO.class)).thenReturn(statsDTO1);
		// Act
		ResultActions perform = mockMvc.perform(post("/stats").content(mapper.writeValueAsString(statsDTO1))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		// Assert
		perform.andExpect(status().isOk());
		perform.andExpect(content().json(mapper.writeValueAsString(statsDTO1)));
	}

}
