package dev.tindersamurai.blinckserver.security.filter.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tindersamurai.blinckserver.security.credentials.JWTLoginCredentials;
import dev.tindersamurai.blinckserver.security.token.service.TokenAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Henry on 22/08/17.
 */
@Slf4j
public final class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {


	private final TokenAuthenticationService tokenAuthService;
	private final Class<? extends JWTLoginCredentials> loginCredentialsClass;


	public JWTLoginFilter(String url,
						  AuthenticationManager authManager,
						  TokenAuthenticationService tokenAuthService,
						  Class<? extends JWTLoginCredentials> loginCredentialsClass) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		this.tokenAuthService = tokenAuthService;
		this.loginCredentialsClass = loginCredentialsClass;
	}



	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
												HttpServletResponse res)
			throws AuthenticationException, IOException {

		JWTLoginCredentials credentials = new ObjectMapper()
				.readValue(req.getInputStream(), loginCredentialsClass);

		log.info("JWT: REQUEST: {}", req);
		log.info("JWT: PRINCIPAL: {}", credentials.getPrincipal());
		log.info("JWT: CREDENTIALS: {}", credentials.getCredentials());
		log.info("JWT: AUTHORITIES: {}", credentials.getAuthorities());

		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						credentials.getPrincipal(),
						credentials.getCredentials(),
						credentials.getAuthorities()
				)
		);
	}



	@Override
	protected void successfulAuthentication(HttpServletRequest req,
											HttpServletResponse res,
											FilterChain chain,
											Authentication auth)
			throws IOException, ServletException {
		log.debug("AUTHENTICATION SUCCESS: {}", auth);
		tokenAuthService.addAuthentication(res, auth);
	}
}