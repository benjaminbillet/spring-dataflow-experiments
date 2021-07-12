package fr.benjaminbillet.dataflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiPollerSource {

	// https://random-data-api.com/api/users/random_user

	public static void main(String[] args) {
		SpringApplication.run(ApiPollerSource.class, args);
	}

}
