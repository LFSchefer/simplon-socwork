package co.simplon.socwork.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.auth0.jwt.algorithms.Algorithm;

@Configuration
public class SecurityConfig {
	
	@Value("${socwork.cors}")
	private String origins;
	
	@Value("${socwork.bcrypt.cost}")
	private int cost;
	
	@Value("${socwork.jwt.secret}")
	private String secret;
	
	@Value("${socwork.jwt.expire}")
	private Integer expire;

	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE").allowedOrigins(origins);
			}
		};
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(BCryptVersion.$2B,cost);
	}
	
	@Bean
	JwtProvider jwtProvider() {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return new JwtProvider(algorithm, expire);
	}

}
