package es.uniovi.asw;

import es.uniovi.asw.dbupdate.ports.InsertP;
import es.uniovi.asw.dbupdate.repositories.VoterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.uniovi.asw.util.Console;

/**
 * Main application
 * 
 * @author Iván
 */
@SpringBootApplication
public class LoadVoters {

	public static void main(String... args) {
		SpringApplication.run(LoadVoters.class, args);
	}
	
	@Bean
	public CommandLineRunner console(VoterRepository voterRepository) {
		return (args) -> {
			InsertP.setVoterRepository(voterRepository);
			Console.start(args);
		};
	
	}

}
