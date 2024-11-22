package co.simplon.socwork.config;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
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
	
	// Authorization server config
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(BCryptVersion.$2B,cost);
	}
	
	@Bean
	JwtProvider jwtProvider() {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return new JwtProvider(algorithm, expire);
	}
	
	// Ressource server config
	
	@Bean
    JwtDecoder jwtDecoder() {
    SecretKey secretKey = new SecretKeySpec(secret.getBytes(),
        "HMACSHA256");
    NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(secretKey)
        .macAlgorithm(MacAlgorithm.HS256).build();
    return decoder;
    }
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors(Customizer.withDefaults())
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(request -> 
				request.requestMatchers(HttpMethod.POST,"/accounts/sign-in","/accounts").anonymous()
					.anyRequest().authenticated())
			.oauth2ResourceServer(oauth -> 
				oauth.jwt(Customizer.withDefaults()));
//			.oauth2ResourceServer(oauth -> 
//				oauth.jwt( jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));
		return http.build();
	}

}
