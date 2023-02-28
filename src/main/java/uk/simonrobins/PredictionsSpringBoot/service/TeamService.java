package uk.simonrobins.PredictionsSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import uk.simonrobins.PredictionsSpringBoot.entity.Team;
import uk.simonrobins.PredictionsSpringBoot.repository.TeamRepository;

import java.util.List;

@Service
public class TeamService
{
    @Autowired
    private TeamRepository repository;

    public Team create(Team team)
    {
        return repository.save(team);
    }

    public Team get(Long teamId)
    {
        return repository.findById(teamId).get();
    }

    public Team findByName(String name)
    {
        return repository.findByName(name);
    }

    public List<Team> get()
    {
        return repository.findAll(Sort.by("name"));
    }

    public Team update(Team team)
    {
        Team existingTeam = repository.findById(team.getId()).get();
        existingTeam.setName(team.getName());
        return repository.save(existingTeam);
    }

    public void delete(Long id)
    {
        repository.deleteById(id);
    }
}