package com.everis.becaeoi.quitSmoking.persistence.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.everis.becaeoi.quitSmoking.persistence.entity.User;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class UserRepositoryIT {

	@Autowired
	private UserRepository repository;

	@Test
	@DatabaseSetup("/db/user/init.xml")
	public void testFindAll() {
		// Act
		List<User> users = (List<User>) repository.findAll();
		// Assert
		assertEquals(2, users.size());
	}
	
	@Test
	@DatabaseSetup("/db/user/init.xml")
	public void testFindById() {
		// Act
		User user = repository.findById("user1").get();
		// Assert
		assertEquals("name1", user.getName());
	}
	
	@Test
	@DatabaseSetup("/db/user/init.xml")
	@ExpectedDatabase(value = "/db/user/afterSavingUser.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void testSave() {
		//Arrange
		User user = new User();
		user.setUsername("igarcia10");
		user.setPassword("gatitos");
		//Act
		repository.save(user);
	}

}
