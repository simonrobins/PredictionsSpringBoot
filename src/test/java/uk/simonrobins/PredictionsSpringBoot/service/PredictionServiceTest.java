package uk.simonrobins.PredictionsSpringBoot.service;

import static org.mockito.Mockito.*;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import uk.simonrobins.PredictionsSpringBoot.entity.Prediction;
import uk.simonrobins.PredictionsSpringBoot.entity.Result;

@SpringBootTest
public class PredictionServiceTest {

    @Mock
    private PredictionService predictionService;

    @Test
    void testCreate() {
        Prediction prediction = new Prediction();
        predictionService.create(prediction);

        verify(predictionService).create(prediction);
        when(predictionService.create(prediction)).thenReturn(prediction);
    }

    @Test
    void testDelete() {

    }

    @Test
    void testGet() {

    }

    @Test
    void testGetByUserId() {

    }

    @Test
    void testPredictionScores() {

    }

    @Test
    void testUpdate() {

    }

    @Test
    void testUpdateResult() {
        Long predictionId = new Random().nextLong();
        Prediction prediction = predictionService.updateResult(predictionId, Result.HOME);

        verify(predictionService).updateResult(any(Long.class), any(Result.class));
        when(predictionService.updateResult(predictionId, Result.HOME)).thenReturn(prediction);
    }
}
