package uk.simonrobins.PredictionsSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.simonrobins.PredictionsSpringBoot.entity.Fixture;
import uk.simonrobins.PredictionsSpringBoot.entity.Team;
import uk.simonrobins.PredictionsSpringBoot.entity.TeamData;
import uk.simonrobins.PredictionsSpringBoot.repository.FixtureRepository;
import uk.simonrobins.PredictionsSpringBoot.repository.TeamRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class FixtureService
{
    @Autowired
    private FixtureRepository fixtureRepository;
    @Autowired
    private TeamRepository teamRepository;

    public Set<Fixture> findAll()
    {
        return fixtureRepository.findAll();
    }

    public Set<Fixture> findResults(Integer round, Long teamId)
    {
        return fixtureRepository.findResults(getDate(), round, teamId);
    }

    public Set<Fixture> findMissing(Integer round, Long teamId)
    {
        return fixtureRepository.findMissing(getDate(), round, teamId);
    }

    public Set<Fixture> findUpcoming(Integer round, Long teamId)
    {
        return fixtureRepository.findUpcoming(getDate(), round, teamId);
    }

    public Set<Integer> findMissingRounds()
    {
        return fixtureRepository.findMissingRounds(getDate());
    }

    public Set<Integer> findAllRounds()
    {
        return fixtureRepository.findAllRounds();
    }

    public Set<Integer> findUpcomingRounds()
    {
        return fixtureRepository.findUpcomingRounds(getDate());
    }

    public Set<Integer> findResultsRounds()
    {
        return fixtureRepository.findResultsRounds(getDate());
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

    public void create(Fixture fixture)
    {
        fixtureRepository.save(fixture);
    }

    public Fixture updateDate(Long id, Date date)
    {
        Fixture fixture = fixtureRepository.findById(id).get();
        fixture.setDate(date);
        return fixtureRepository.save(fixture);
    }

    public Fixture updateHomeGoals(Long id, Integer goals)
    {
        Fixture fixture = fixtureRepository.findById(id).get();
        fixture.setHomeGoals(goals);
        return fixtureRepository.save(fixture);
    }

    public Fixture updateAwayGoals(Long id, Integer goals)
    {
        Fixture fixture = fixtureRepository.findById(id).get();
        fixture.setAwayGoals(goals);
        return fixtureRepository.save(fixture);
    }

    private Date getDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);

        return calendar.getTime();
    }
}