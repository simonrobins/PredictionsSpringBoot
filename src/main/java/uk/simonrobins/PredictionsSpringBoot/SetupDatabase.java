package uk.simonrobins.PredictionsSpringBoot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import uk.simonrobins.PredictionsSpringBoot.entity.Fixture;
import uk.simonrobins.PredictionsSpringBoot.entity.Fixture2;
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

	public void setupTeamsAndFixtures(String url) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssX");

		try {
			for (Fixture2 f : fetchFixtures(url)) {
				Team homeTeam = readOrCreateTeam(f.HomeTeam, f.Location);
				Team awayTeam = readOrCreateTeam(f.AwayTeam);
				Date date = format.parse(f.DateUtc);
				Fixture fixture = fixtureService.findFixtureBetween(homeTeam, awayTeam);
				if(fixture == null)
				{
					fixture = new Fixture(f.MatchNumber, f.RoundNumber, date, homeTeam, awayTeam, f.HomeTeamScore, f.AwayTeamScore);
				}
				else
				{
					fixture.setHomeGoals(f.HomeTeamScore);
					fixture.setAwayGoals(f.AwayTeamScore);
				}

				fixtureService.create(fixture);
			}
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

	private List<Fixture2> fetchFixtures(String url) {
		WebClient client = WebClient.create();

		return client
				.get()
				.uri(url)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Fixture2>>() {})
				.block();
	}

	private Team readOrCreateTeam(String teamname) {
		return readOrCreateTeam(teamname, null);
	}

	private Team readOrCreateTeam(String teamname, String stadium) {
		Team team = teamService.findByName(teamname);
		if (null == team) {
			team = new Team(teamname);
		}

		if (stadium != null)
			team.setStadium(stadium);

		teamService.create(team);

		return team;
	}
}
