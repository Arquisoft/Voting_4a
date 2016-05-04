package es.uniovi.asw;

import es.uniovi.asw.dbupdate.ports.InsertP;
import es.uniovi.asw.dbupdate.repositories.VoterRepository;
import es.uniovi.asw.dbupdate.repositories.VotingPlaceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.uniovi.asw.util.Console;

/**
 * Main application
 * 
 * @author Iván
 */
@EnableAutoConfiguration
@SpringBootApplication
public class LoadVoters {

	public static void main(String... args) {
		SpringApplication.run(LoadVoters.class, args);
	}
	
	@Bean
	public CommandLineRunner console(VoterRepository voterRepository, VotingPlaceRepository votingPlaceRepository) {
		return (args) -> {
			InsertP.setVoterRepository(voterRepository);
			InsertP.setVotingPlaceRepository(votingPlaceRepository);
			Console.start(args);
		};
	
	}

}
