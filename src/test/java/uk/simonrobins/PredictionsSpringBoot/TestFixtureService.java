package uk.simonrobins.PredictionsSpringBoot;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import uk.simonrobins.PredictionsSpringBoot.controller.rest.ResultController;
import uk.simonrobins.PredictionsSpringBoot.service.FixtureService;

@SpringBootTest
public class TestFixtureService {

	@InjectMocks
	private ResultController controller;

	@Mock
	FixtureService fixtureService;

	@Test
	public void testFixtureServiceUpdateFixtureDate() throws Exception {
		assertThat(controller).isNotNull();
		Long id = new Random(1000000).nextLong();
		Date now = new Date();
		controller.updateFixtureDate(id, now);
		verify(fixtureService).updateDate(id, now);
	}
}