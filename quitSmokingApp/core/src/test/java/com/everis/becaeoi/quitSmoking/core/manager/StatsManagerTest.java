package com.everis.becaeoi.quitSmoking.core.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.everis.becaeoi.quitSmoking.persistence.entity.Stats;
import com.everis.becaeoi.quitSmoking.persistence.repository.StatsRepository;

@RunWith(MockitoJUnitRunner.class)
public class StatsManagerTest {
	
	@InjectMocks
	private StatsManager manager;
	
	@Mock
	private StatsRepository repository;
	
	@Test
	public void testFindAll() {
		//Arrange
		final Iterable<Stats> iterable = new ArrayList<>();
		Mockito.when(repository.findAll()).thenReturn(iterable);
		//Act
		List<Stats> users = manager.findAll();
		//Assert
		Assert.assertEquals((List<Stats>) iterable, users);
	}
	
	@Test
	public void testSave() {
		//Arrange
		final Stats stats = new Stats();
		final Stats stats2 = new Stats();
		Mockito.when(repository.save(stats)).thenReturn(stats2);
		//Act
		Stats resultUser = manager.save(stats);
		//Assert
		Assert.assertEquals(stats2, resultUser);
	}
	
	@Test
	public void testShowStatsInfo() {
		//Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
		final Stats stats = new Stats();
		stats.setId(1L);
		stats.setQuitSmoking(calendar.getTime());
		stats.setSmokesPackPrice(5.0);
		stats.setSmokingYears(10);
		stats.setSmokesPerDay(20);
		Mockito.when(repository.findByUserUsername("username")).thenReturn(stats);
		//Act
		final StatsInfo resultStatsInfo = manager.showStatsInfo("username");
		//Assert
		Assert.assertNotNull(resultStatsInfo.getTimeWithoutSmoking());
		Assert.assertEquals(String.class, resultStatsInfo.getTimeWithoutSmoking().getClass());
		Assert.assertNotNull(resultStatsInfo.getSmokesSaved());
		Assert.assertEquals(Integer.class, resultStatsInfo.getSmokesSaved().getClass());
		Assert.assertNotNull(resultStatsInfo.getMoneySaved());
		Assert.assertEquals(Double.class, resultStatsInfo.getMoneySaved().getClass());
		Assert.assertNotNull(resultStatsInfo.getLifeTimeGained());
		Assert.assertEquals(Double.class, resultStatsInfo.getLifeTimeGained().getClass());
		Assert.assertNotNull(resultStatsInfo.getTimeSaved());
		Assert.assertEquals(Integer.class, resultStatsInfo.getTimeSaved().getClass());
	}

}
