package co.simplon.socwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
public class SocworkApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocworkApiApplication.class, args);
	}

}
