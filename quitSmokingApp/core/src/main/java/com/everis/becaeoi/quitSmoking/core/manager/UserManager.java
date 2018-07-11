package com.everis.becaeoi.quitSmoking.core.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.becaeoi.quitSmoking.persistence.entity.User;
import com.everis.becaeoi.quitSmoking.persistence.repository.UserRepository;

@Service
public class UserManager {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return (List<User>) repository.findAll();
	}
	
	public User findById(String id){
		return repository.findById(id).get();
	}
	
	public User save(User user) {
		return repository.save(user);
	}

}
