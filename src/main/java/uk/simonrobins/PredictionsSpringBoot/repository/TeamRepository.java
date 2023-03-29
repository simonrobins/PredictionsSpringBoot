package uk.simonrobins.PredictionsSpringBoot.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import uk.simonrobins.PredictionsSpringBoot.entity.Team;

import org.springframework.data.domain.Sort;

public interface TeamRepository extends CrudRepository<Team, Long>
{
    Set<Team> findAll(Sort sort);

    Team findByName(String name);
}