package co.simplon.socwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SocworkApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocworkApiApplication.class, args);
	}

}
