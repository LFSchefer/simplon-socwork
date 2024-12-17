package co.simplon.socwork.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

import co.simplon.socwork.entities.Role;

public class JwtProvider {
	
	private final Algorithm algorithm;
	
	private final Integer expire;
	
	private final String issuer;

	public JwtProvider(Algorithm algorithm, Integer expire, String issuer) {
		this.algorithm = algorithm;
		this.expire = expire;
		this.issuer = issuer;
	}

	public String create(String subject, Set<Role> setRoles) {
		String[] roles = setRoles.stream().map(r -> r.getName()).toArray(String[]::new);
		Instant instant = Instant.now();
		Builder builder = JWT.create()
				.withIssuedAt(instant)
				.withSubject(subject)
				.withArrayClaim("roles", roles)
				.withIssuer(issuer);
		if (expire != null) {
			builder.withExpiresAt(instant.plus(expire, ChronoUnit.MINUTES));
		}
		return builder.sign(algorithm);
	}

}
