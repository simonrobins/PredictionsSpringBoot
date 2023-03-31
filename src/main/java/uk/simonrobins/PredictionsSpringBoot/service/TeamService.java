package uk.simonrobins.PredictionsSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import uk.simonrobins.PredictionsSpringBoot.entity.Team;
import uk.simonrobins.PredictionsSpringBoot.entity.TeamData;
import uk.simonrobins.PredictionsSpringBoot.repository.FixtureRepository;
import uk.simonrobins.PredictionsSpringBoot.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TeamService
{
    @Autowired
    private FixtureRepository fixtureRepository;
    @Autowired
    private TeamRepository teamRepository;

    public Team create(Team team)
    {
        return teamRepository.save(team);
    }

    public Team findById(Long teamId)
    {
        return teamRepository.findById(teamId).get();
    }

    public Team findByName(String name)
    {
        return teamRepository.findByName(name);
    }

    public Set<Team> findAll()
    {
        return teamRepository.findAll(Sort.by("name"));
    }

    public Team update(Team team)
    {
        Team existingTeam = teamRepository.findById(team.getId()).get();
        existingTeam.setName(team.getName());
        return teamRepository.save(existingTeam);
    }

    public void delete(Long id)
    {
        teamRepository.deleteById(id);
    }

    public List<TeamData> resultsTable()
    {
        List<TeamData> teamData = new ArrayList<>();
        for(Long[] row : fixtureRepository.resultsTable())
        {
            Team team = teamRepository.findById(row[0]).get();

            teamData.add(new TeamData(team, row));
        }

        return teamData;
    }
}