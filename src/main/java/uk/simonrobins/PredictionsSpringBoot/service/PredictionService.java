package uk.simonrobins.PredictionsSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import uk.simonrobins.PredictionsSpringBoot.entity.Prediction;
import uk.simonrobins.PredictionsSpringBoot.entity.Result;
import uk.simonrobins.PredictionsSpringBoot.entity.UserAndScore;
import uk.simonrobins.PredictionsSpringBoot.repository.PredictionRepository;

@Service
public class PredictionService
{
    @Autowired
    private PredictionRepository predictionRepository;

    public Prediction create(Prediction prediction)
    {
        return predictionRepository.save(prediction);
    }

    public Prediction get(Long predictionId)
    {
        return predictionRepository.findById(predictionId).get();
    }

    public List<Prediction> getByUserId(Long id)
    {
        return predictionRepository.findAllByUserId(id, Sort.by("user.lastName", "user.firstName", "fixture.date"));
    }

    public List<UserAndScore> predictionScores()
    {
        return predictionRepository.predictionScores();
    }

    public Prediction update(Prediction prediction)
    {
        Prediction existingPrediction = predictionRepository.findById(prediction.getId()).get();
        existingPrediction.setUser(prediction.getUser());
        existingPrediction.setFixture(prediction.getFixture());
        existingPrediction.setResult(prediction.getResult());
        return predictionRepository.save(existingPrediction);
    }

    public void updateResult(Long id, Result result)
    {
        Prediction prediction = predictionRepository.findById(id).get();
        prediction.setResult(result);
        predictionRepository.save(prediction);
    }

    public void delete(Long predictionId)
    {
        predictionRepository.deleteById(predictionId);
    }
}