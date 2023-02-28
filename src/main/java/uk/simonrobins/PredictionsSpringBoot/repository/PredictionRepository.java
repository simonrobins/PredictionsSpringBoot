package uk.simonrobins.PredictionsSpringBoot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import uk.simonrobins.PredictionsSpringBoot.entity.Prediction;
import uk.simonrobins.PredictionsSpringBoot.entity.UserAndScore;

public interface PredictionRepository extends CrudRepository<Prediction, Long>
{
    List<Prediction> findAllByUserId(Long userId, Sort sort);

    @Query(value="SELECT new uk.simonrobins.PredictionsSpringBoot.entity.UserAndScore(p.user, count(*) score) " +
                "FROM Prediction p INNER JOIN p.fixture f " +
                "WHERE p.result = f.result " +
                "GROUP BY user " +
                "ORDER BY score DESC, p.user.lastName, p.user.firstName")
    List<UserAndScore> predictionScores();
}