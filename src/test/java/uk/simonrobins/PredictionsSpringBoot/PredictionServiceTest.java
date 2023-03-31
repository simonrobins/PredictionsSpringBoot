package uk.simonrobins.PredictionsSpringBoot;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import uk.simonrobins.PredictionsSpringBoot.service.PredictionService;

@SpringBootTest
public class PredictionServiceTest {
	@Mock
	PredictionService predictionService;

	@Test
	public void testFixtureServiceCreate() throws Exception {
		predictionService.create(null);
	}
}