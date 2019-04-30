package dev.tindersamurai.blinckserver.security.token.processor;

import org.springframework.security.core.Authentication;

/**
 * @author Henry on 01/09/17.
 */
public interface TokenAuthenticationProcessor {

	void addAuthentication(Authentication authentication, String token);

	Authentication processAuthentication(Authentication authentication, String token);
}
