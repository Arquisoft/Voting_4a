package es.uniovi.asw;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;

import es.uniovi.asw.model.Voter;

@SpringBootApplication

@ActiveProfiles("test")
public class TestApp {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TestApp.class);
		app.run(args);
	}

	@Test
	public void evalAdd() {
		Voter voter = new Voter("Manolo", "1234", "12345324A", 12, "aAbBcCdD");
		assertEquals(voter.getName(), "Manolo");
	}

}
