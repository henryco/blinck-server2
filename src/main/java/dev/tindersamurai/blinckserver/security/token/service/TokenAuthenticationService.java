package dev.tindersamurai.blinckserver.security.token.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import dev.tindersamurai.blinckserver.security.token.processor.TokenAuthenticationProcessor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Henry on 23/08/17.
 */ @Slf4j
public abstract class TokenAuthenticationService {


	protected abstract TokenAuthenticationProcessor getProcessor();

	protected abstract String getTokenSecret();

	protected String getDefaultRole() {
		return "ROLE_USER";
	}

	protected String getTokenHeader() {
		return HttpHeaders.AUTHORIZATION;
	}

	protected String getTokenPrefix() {
		return "Bearer";
	}

	protected Long getExpirationTime() {
		return 864_000_000L;
	}


	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private static final class TokenPayload {
		private String username;
		private String[] authorities;
	}




	public final void addAuthentication(HttpServletResponse res, Authentication auth) {

		Stream<String> stream = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority);

		val JWT = createAuthenticationToken(auth.getName(), stream.toArray(String[]::new));
		res.addHeader(getTokenHeader(), getTokenPrefix() + " " + JWT);

		if (getProcessor() != null) getProcessor().addAuthentication(auth, JWT);
	}



	public final Authentication getAuthentication(HttpServletRequest request) {
		return getAuthentication(request.getHeader(getTokenHeader()));
	}


	@SuppressWarnings("ConstantConditions")
	public final Authentication getAuthentication(String jsonWebToken) {

		try {
			val payload = readAuthenticationToken(jsonWebToken);
			val auth = new UsernamePasswordAuthenticationToken(
					payload.username,
					null,
					grantAuthorities(payload.authorities)
			);

			if (getProcessor() == null) return auth;
			return getProcessor().processAuthentication(auth, jsonWebToken);

		} catch (JwtException | NullPointerException e) {
			log.error("Cannot process authentication for JWT: " + jsonWebToken, e);
			return null;
		}
	}



	private String createAuthenticationToken(String username, String ... authorities) {

		try {
			val payload = new TokenPayload(username, authorities);
			return parseTo(new ObjectMapper().writeValueAsString(payload));
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Cannot create JWT string", e);
		}
	}


	private TokenPayload readAuthenticationToken(String token) {
		try {
			return new ObjectMapper().readValue(parseFrom(token), TokenPayload.class);
		} catch (IOException e) {
			log.error("Error while reading authentication token", e);
			return null;
		}
	}


	private String parseTo(String payload) {
		return Jwts.builder()
				.setSubject(payload)
				.setExpiration(new Date(System.currentTimeMillis() + getExpirationTime()))
				.signWith(SignatureAlgorithm.HS512, getTokenSecret())
		.compact();
	}


	private String parseFrom(String token) {
		if (token == null) return null;

		return Jwts.parser()
				.setSigningKey(getTokenSecret())
				.parseClaimsJws(token.replace(getTokenPrefix(), "")).getBody()
		.getSubject();
	}


	private Collection<? extends GrantedAuthority> grantAuthorities(String ... authorities) {

		try {
			return Arrays.stream(authorities)
					.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());
		} catch (Exception e) {
			log.error("Cannot grant authorities", e);
		}
		return defaultAuthorities();
	}


	private Collection<? extends GrantedAuthority> defaultAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(getDefaultRole()));
	}


}