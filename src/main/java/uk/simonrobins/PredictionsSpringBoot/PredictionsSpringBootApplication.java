package uk.simonrobins.PredictionsSpringBoot;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PredictionsSpringBootApplication implements CommandLineRunner {

	private static final Logger log = Logger.getLogger(PredictionsSpringBootApplication.class.getName());

	@Autowired
	SetupDatabase setupDatabase;

	public static void main(String[] args) {
		log.info(PredictionsSpringBootApplication.class.getName() + " starting up.");
		SpringApplication.run(PredictionsSpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) {
		setupDatabase.setupTeamsAndFixtures("https://fixturedownload.com/feed/json/epl-2022");
		setupDatabase.setupUsers("users.csv");
		setupDatabase.setupPredictions();
	}
}
