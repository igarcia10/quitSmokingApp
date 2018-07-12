package com.everis.becaeoi.quitSmoking.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.becaeoi.quitSmoking.core.manager.UserManager;
import com.everis.becaeoi.quitSmoking.persistence.entity.User;
import com.everis.becaeoi.quitSmoking.service.DTO.UserDTO;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserManager manager;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@GetMapping("/")
	public List<UserDTO> findAll(){
		List<User> userList = manager.findAll();
		List<UserDTO> userDTOList = new ArrayList<>();
		for (User user : userList) {
			userDTOList.add(mapper.map(user, UserDTO.class));
		}
		return userDTOList;
	}
	
	@GetMapping("/{id}")
	public UserDTO findByUsername(@RequestParam String username){
		return mapper.map(manager.findByUsername(username),UserDTO.class);
	}
	
	@PostMapping("/")
	public UserDTO save(@RequestBody UserDTO dto) {
		return mapper.map(manager.save(mapper.map(dto, User.class)),UserDTO.class);
	}

}
