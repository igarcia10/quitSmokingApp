package com.everis.becaeoi.quitSmoking.core.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.everis.becaeoi.quitSmoking.persistence.entity.User;
import com.everis.becaeoi.quitSmoking.persistence.repository.UserRepository;


@RunWith(MockitoJUnitRunner.class)
public class UserManagerTest {
	
	@InjectMocks
	private UserManager manager;
	
	@Mock
	private UserRepository repository;
	
	@Test
	public void testFindAll() {
		//Arrange
		final Iterable<User> iterable = new ArrayList<>();
		Mockito.when(repository.findAll()).thenReturn(iterable);
		//Act
		List<User> users = manager.findAll();
		//Assert
		Assert.assertEquals((List<User>) iterable, users);
	}
	
	@Test
	public void testFindByUsername() {
		//Arrange
		final User user = new User();
		Mockito.when(repository.findByUsername("username")).thenReturn(user);
		//Act
		User resultUser = manager.findByUsername("username");
		//Assert
		Assert.assertEquals(user, resultUser);
	}
	
	@Test
	public void testSave() {
		//Arrange
		final User user = new User();
		final User user2 = new User();
		Mockito.when(repository.save(user)).thenReturn(user2);
		//Act
		User resultUser = manager.save(user);
		//Assert
		Assert.assertEquals(user2, resultUser);
	}

}
