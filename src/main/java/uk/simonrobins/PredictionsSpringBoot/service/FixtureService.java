package uk.simonrobins.PredictionsSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.simonrobins.PredictionsSpringBoot.entity.Fixture;
import uk.simonrobins.PredictionsSpringBoot.repository.FixtureRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Service
public class FixtureService
{
    @Autowired
    private FixtureRepository fixtureRepository;

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