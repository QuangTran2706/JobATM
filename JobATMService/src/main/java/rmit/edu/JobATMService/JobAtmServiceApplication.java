package rmit.edu.JobATMService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class JobAtmServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobAtmServiceApplication.class, args);
	}

}
