package com.everis.becaeoi.quitSmoking.service.controller;

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
	
	@SuppressWarnings("unchecked")
	@GetMapping("/")
	public List<UserDTO> findAll(){
		return (List<UserDTO>) mapper.map(manager.findAll(),UserDTO.class);
	}
	
	@GetMapping("/{id}")
	public UserDTO findById(@RequestParam String id){
		return mapper.map(manager.findById(id),UserDTO.class);
	}
	
	@PostMapping("/")
	public UserDTO save(@RequestBody UserDTO dto) {
		return mapper.map(manager.save(mapper.map(dto, User.class)),UserDTO.class);
	}

}
