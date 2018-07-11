package com.everis.becaeoi.quitSmoking.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.everis.becaeoi.quitSmoking.persistence.entity.User;

public interface UserRepository extends CrudRepository<User, String>{

}
