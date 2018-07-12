package com.everis.becaeoi.quitSmoking.service.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.everis.becaeoi.quitSmoking.core.manager.StatsManager;
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

}
