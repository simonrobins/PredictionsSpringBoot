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

    public List<Prediction> getByUserId(Long id)
    {
        return predictionRepository.findAllByUserId(id, Sort.by("user.lastName", "user.firstName", "fixture.date"));
    }

    public List<Prediction> getByUserIdAndRound(Long id, Integer round)
    {
        return predictionRepository.findAllByUserIdAndRound(id, round);
    }

    public List<UserAndScore> predictionScores()
    {
        return predictionRepository.predictionScores();
    }

    public Prediction updateResult(Long id, Result result)
    {
        Prediction prediction = predictionRepository.findById(id).get();
        prediction.setResult(result);
        return predictionRepository.save(prediction);
    }
}