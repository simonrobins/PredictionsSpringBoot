package uk.simonrobins.PredictionsSpringBoot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.simonrobins.PredictionsSpringBoot.entity.Fixture;
import uk.simonrobins.PredictionsSpringBoot.entity.Prediction;
import uk.simonrobins.PredictionsSpringBoot.entity.Result;
import uk.simonrobins.PredictionsSpringBoot.entity.Team;
import uk.simonrobins.PredictionsSpringBoot.entity.User;
import uk.simonrobins.PredictionsSpringBoot.service.FixtureService;
import uk.simonrobins.PredictionsSpringBoot.service.PredictionService;
import uk.simonrobins.PredictionsSpringBoot.service.TeamService;
import uk.simonrobins.PredictionsSpringBoot.service.UserService;

@Component
public class SetupDatabase {

	static final int MatchNumberField = 0,
			RoundNumberField = 1,
			DateField = 2,
			LocationField = 3,
			HomeTeamField = 4,
			AwayTeamField = 5,
			ResultField = 6;

	static final int InitialsField = 0,
			FirstNameField = 1,
			LastNameField = 2,
			EmailField = 3,
			PasswordField = 4;

	@Autowired
	private FixtureService fixtureService;
	@Autowired
	private PredictionService predictionService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private UserService userService;

	public void setupTeamsAndFixtures(String filename) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			br.lines().forEach(line -> {
				try {
					// Let's skip the header line if it exists
					if (line.startsWith("Match Number") == false) {
						// Match Number,Round Number,Date,Location,Home Team,Away Team,Result
						String[] row = line.split(",");
						int matchNumber = Integer.parseInt(row[MatchNumberField]);
						int roundNumber = Integer.parseInt(row[RoundNumberField]);
						Date date = format.parse(row[DateField]);
						String location = row[LocationField];
						Team homeTeam = readOrCreateTeam(row[HomeTeamField]);
						Team awayTeam = readOrCreateTeam(row[AwayTeamField]);
						Integer homeGoals = null;
						Integer awayGoals = null;
						Result result = null;
						// Do we have the scores at the end of the line?
						if (row.length > ResultField) {
							String[] goals = row[ResultField].split(" - ");
							homeGoals = Integer.parseInt(goals[0]);
							awayGoals = Integer.parseInt(goals[1]);
							if (homeGoals > awayGoals)
								result = Result.HOME;
							else if (homeGoals < awayGoals)
								result = Result.AWAY;
							else
								result = Result.DRAW;
						}

						Fixture fixture = new Fixture(matchNumber, roundNumber, date, location, homeTeam, awayTeam,
								homeGoals, awayGoals, result);
						fixtureService.create(fixture);
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			});
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void setupUsers(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			br.lines().forEach(line -> {
				// Let's skip the header line if it exists
				if (line.startsWith("Initials") == false) {
					// Initials, First Name, Last Name, Email, Password
					String[] row = line.split(",");
					User user = new User(
							row[InitialsField],
							row[FirstNameField],
							row[LastNameField],
							row[EmailField],
							row[PasswordField]);
					userService.create(user);
				}

			});
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void setupPredictions() {
		Date today = new Date();
		Random random = new Random();
		Result[] results = Result.values();

		// For every user insert every fixture into predictions
		for (User user : userService.findAll()) {
			for (Fixture fixture : fixtureService.findAll()) {
				int resultValue = random.nextInt(results.length);
				Result result = null;
				if (fixture.getDate().before(today))
					result = results[resultValue];

				Prediction prediction = new Prediction(user, fixture, result);

				predictionService.create(prediction);
			}
		}
	}

	private Team readOrCreateTeam(String teamname) {
		Team team = teamService.findByName(teamname);
		if (null == team) {
			team = new Team(teamname);
			teamService.create(team);
		}

		return team;
	}
}
