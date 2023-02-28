package uk.simonrobins.PredictionsSpringBoot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import uk.simonrobins.PredictionsSpringBoot.entity.Team;

import org.springframework.data.domain.Sort;

public interface TeamRepository extends CrudRepository<Team, Long>
{
    List<Team> findAll(Sort sort);

    Team findByName(String name);
}