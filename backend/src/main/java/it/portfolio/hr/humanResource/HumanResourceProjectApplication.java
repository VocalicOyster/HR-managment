package it.portfolio.hr.humanResource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HumanResourceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HumanResourceProjectApplication.class, args);
	}

}
