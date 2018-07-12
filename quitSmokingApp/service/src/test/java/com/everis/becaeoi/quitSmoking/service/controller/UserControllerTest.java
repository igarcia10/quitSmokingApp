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

import com.everis.becaeoi.quitSmoking.core.manager.UserManager;
import com.everis.becaeoi.quitSmoking.persistence.entity.User;
import com.everis.becaeoi.quitSmoking.service.DTO.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserControllerTest {

	@InjectMocks
	private UserController controller;

	@Mock
	private UserManager manager;

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
		ResultActions perform = mockMvc.perform(get("/users"));
		// Assert
		perform.andExpect(status().isOk());
	}

	@Test
	public void testFindAllWithContent() throws Exception {
		// Arrange
		User user1 = new User();
		user1.setName("pepe");
		UserDTO userDTO1 = new UserDTO();
		userDTO1.setName("pepe");
		User user2 = new User();
		user2.setName("juan");
		UserDTO userDTO2 = new UserDTO();
		userDTO2.setName("juan");
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		List<UserDTO> userDTOs = new ArrayList<>();
		userDTOs.add(userDTO1);
		userDTOs.add(userDTO2);
		Mockito.when(manager.findAll()).thenReturn(users);
		Mockito.when(dozerMapper.map(user1, UserDTO.class)).thenReturn(userDTO1);
		Mockito.when(dozerMapper.map(user2, UserDTO.class)).thenReturn(userDTO2);
		// Act
		ResultActions perform = mockMvc.perform(get("/users"));
		// Assert
		perform.andExpect(status().isOk());
		perform.andExpect(content().json(mapper.writeValueAsString(userDTOs)));
	}

	@Test
	public void testFindByUsername() throws Exception {
		// Arrange
		Mockito.when(manager.findByUsername("username")).thenReturn(null);
		// Act
		ResultActions perform = mockMvc.perform(get("/users/username"));
		// Assert
		perform.andExpect(status().isOk());
	}

	@Test
	public void testFindByUsernameWithContent() throws Exception {
		// Arrange
		User user1 = new User();
		user1.setName("pepe");
		UserDTO userDTO1 = new UserDTO();
		userDTO1.setName("pepe");
		Mockito.when(manager.findByUsername("pepe")).thenReturn(user1);
		Mockito.when(dozerMapper.map(user1, UserDTO.class)).thenReturn(userDTO1);
		// Act
		ResultActions perform = mockMvc.perform(get("/users/pepe"));
		// Assert
		perform.andExpect(status().isOk());
		perform.andExpect(content().json(mapper.writeValueAsString(userDTO1)));
	}

	@Test
	public void testSave() throws Exception {
		// Arrange
		Mockito.when(manager.save(null)).thenReturn(null);
		// Act
		ResultActions perform = mockMvc.perform(get("/users"));
		// Assert
		perform.andExpect(status().isOk());
	}

	@Test
	public void testSaveWithContent() throws Exception {
		// Arrange
		User user1 = new User();
		user1.setName("pepe");
		UserDTO userDTO1 = new UserDTO();
		userDTO1.setName("pepe");
		Mockito.when(dozerMapper.map(Mockito.any(UserDTO.class), Mockito.any())).thenReturn(user1);
		Mockito.when(manager.save(user1)).thenReturn(user1);
		Mockito.when(dozerMapper.map(user1, UserDTO.class)).thenReturn(userDTO1);
		// Act
		ResultActions perform = mockMvc.perform(post("/users").content(mapper.writeValueAsString(userDTO1))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		// Assert
		perform.andExpect(status().isOk());
		perform.andExpect(content().json(mapper.writeValueAsString(userDTO1)));
	}

}
