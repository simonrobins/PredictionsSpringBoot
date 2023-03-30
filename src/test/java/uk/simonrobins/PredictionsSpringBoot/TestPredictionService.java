package uk.simonrobins.PredictionsSpringBoot;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import uk.simonrobins.PredictionsSpringBoot.controller.rest.ResultController;
import uk.simonrobins.PredictionsSpringBoot.entity.Result;
import uk.simonrobins.PredictionsSpringBoot.service.PredictionService;

@SpringBootTest
public class TestPredictionService {

	@InjectMocks
	private ResultController controller;

	@Mock
	PredictionService predictionService;

	@Test
	public void testFixtureServiceCreate() throws Exception {
		assertThat(controller).isNotNull();
		Long id = new Random(1000000).nextLong();
		Result result = Result.AWAY;
		controller.updatePredictionResult(id, result);
		verify(predictionService).updateResult(id, result);
	}

	@Test
	public void testFixtureServiceUpdatePredictionResult() throws Exception {
		assertThat(controller).isNotNull();
		Long id = new Random(1000000).nextLong();
		Result result = Result.AWAY;
		controller.updatePredictionResult(id, result);
		verify(predictionService).updateResult(id, result);
	}
}