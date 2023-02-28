package uk.simonrobins.PredictionsSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import uk.simonrobins.PredictionsSpringBoot.entity.Fixture;
import uk.simonrobins.PredictionsSpringBoot.entity.Result;
import uk.simonrobins.PredictionsSpringBoot.entity.Team;
import uk.simonrobins.PredictionsSpringBoot.entity.TeamData;
import uk.simonrobins.PredictionsSpringBoot.repository.FixtureRepository;
import uk.simonrobins.PredictionsSpringBoot.repository.TeamRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FixtureService
{
    @Autowired
    private FixtureRepository fixtureRepository;
    @Autowired
    private TeamRepository teamRepository;

    public List<Fixture> findAll()
    {
        return fixtureRepository.findAll(Sort.by("date"));
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

    public void updateDate(Long id, Date date)
    {
        Fixture fixture = fixtureRepository.findById(id).get();
        fixture.setDate(date);
        fixtureRepository.save(fixture);
    }

    public void updateResult(Long id, Result result)
    {
        Fixture fixture = fixtureRepository.findById(id).get();
        fixture.setResult(result);
        fixtureRepository.save(fixture);
    }
}