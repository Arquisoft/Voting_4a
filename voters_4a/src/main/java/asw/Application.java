package asw;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import asw.DBRepository.VoterRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = VoterRepository.class)
@EnableAutoConfiguration
public class Application {
	
    public static void main(String[] args) {
    	SpringApplication.run(Application.class, args);
    }
}