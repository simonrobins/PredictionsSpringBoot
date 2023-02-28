package uk.simonrobins.PredictionsSpringBoot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.simonrobins.PredictionsSpringBoot.entity.Fixture;
import uk.simonrobins.PredictionsSpringBoot.entity.Result;
import uk.simonrobins.PredictionsSpringBoot.entity.Team;
import uk.simonrobins.PredictionsSpringBoot.service.FixtureService;
import uk.simonrobins.PredictionsSpringBoot.service.TeamService;

@Component
public class SetupDatabase {
    private class ResultsHeader
	{
		static final int MatchNumber = 0;
		static final int RoundNumber = 1;
		static final int Date = 2;
		static final int Location = 3;
		static final int HomeTeam = 4;
		static final int AwayTeam = 5;
		static final int Result = 6;
	}

	@Autowired
	private TeamService teamService;
	@Autowired
	private FixtureService fixtureService;

    public void setupTeamsAndFixtures(String filename)
	{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		try (BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
			br.lines().forEach(line ->
			{
				try
				{
					// Let's skip the header line if it exists
					if (line.startsWith("Match Number") == false)
					{
						// Match Number,Round Number,Date,Location,Home Team,Away Team,Result
						String[] row = line.split(",");
						int matchNumber = Integer.parseInt(row[ResultsHeader.MatchNumber]);
						int roundNumber = Integer.parseInt(row[ResultsHeader.RoundNumber]);
						Date date = format.parse(row[ResultsHeader.Date]);
						String location = row[ResultsHeader.Location];
						Team homeTeam = readOrCreateTeam(row[ResultsHeader.HomeTeam]);
						Team awayTeam = readOrCreateTeam(row[ResultsHeader.AwayTeam]);
						Integer homeGoals = null;
						Integer awayGoals = null;
						Result result = null;
						// Do we have the scores at the end of the line?
						if (row.length > ResultsHeader.Result)
						{
							String[] goals = row[ResultsHeader.Result].split(" - ");
							homeGoals = Integer.parseInt(goals[0]);
							awayGoals = Integer.parseInt(goals[1]);
							if (homeGoals > awayGoals)
								result = Result.HOME;
							else if (homeGoals < awayGoals)
								result = Result.AWAY;
							else
								result = Result.DRAW;
						}

						Fixture fixture = new Fixture(matchNumber, roundNumber, date, location, homeTeam, awayTeam, homeGoals, awayGoals, result);
						fixtureService.create(fixture);
					}
				}
				catch (Exception ex)
				{
					System.out.println(ex);
				}
			});
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
	}

	private Team readOrCreateTeam(String teamname)
	{
		Team team = teamService.findByName(teamname);
		if (null == team)
		{
			team = new Team(teamname);
			teamService.create(team);
		}

		return team;
	}
}
