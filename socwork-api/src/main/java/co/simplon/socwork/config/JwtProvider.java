package co.simplon.socwork.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtProvider {
	
	private final Algorithm algorithm;
	
	private final Integer expire;

	public JwtProvider(Algorithm algorithm, Integer expire) {
		this.algorithm = algorithm;
		this.expire = expire;
	}

	public String create(String subject) {
		Instant instant = Instant.now();
		Builder builder = JWT.create()
				.withIssuedAt(instant)
				.withSubject(subject);
		if (expire != null) {
			builder.withExpiresAt(instant.plus(expire, ChronoUnit.MINUTES));
		}
		return builder.sign(algorithm);
	}

}
