package com.everis.becaeoi.quitSmoking.persistence.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import com.everis.becaeoi.quitSmoking.persistence.entity.Stats;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class StatsRepositoryIT {

	@Autowired
	private StatsRepository repository;

	@Test
	@DatabaseSetup("/db/stats/init.xml")
	public void testFindAll() {
		// Act
		List<Stats> stats = (List<Stats>) repository.findAll();
		// Assert
		assertEquals(2, stats.size());
	}

	@Test
	@DatabaseSetup("/db/stats/init.xml")
	public void testFindById() {
		// Act
		Stats stats = repository.findById(1L).get();
		// Assert
		assertEquals(Integer.valueOf(20), stats.getSmokesPerDay());
	}

	@Test
	@DatabaseSetup("/db/stats/init.xml")
	public void testFindByUser() {
		// Act
		Stats stats = repository.findByUserUsername("user1");
		// Assert
		assertEquals(Integer.valueOf(20), stats.getSmokesPerDay());
	}

	@Test
	@DatabaseSetup("/db/stats/init.xml")
	public void testSave() {
		// Arrange
		Stats stats = new Stats();
		stats.setSmokesPackPrice(5.0);
		stats.setSmokingYears(10);
		stats.setSmokesPerDay(20);
		// Act
		repository.save(stats);
		// Assert
		List<Stats> list = (List<Stats>) repository.findAll();
		assertEquals(3, list.size());
		assertNotNull(list.get(2).getId());
	}
}
