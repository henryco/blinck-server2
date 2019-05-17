package dev.tindersamurai.blinckserver.security.token.service;

import dev.tindersamurai.blinckserver.properties.SecurityAppProperties;
import dev.tindersamurai.blinckserver.security.token.processor.TokenAuthenticationProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


/**
 * @author Henry on 22/08/17.
 */

@Service
@Qualifier("UserTokenAuthService")
public final class UserTokenAuthService extends TokenAuthenticationService {

	private final TokenAuthenticationProcessor processor;
	private final SecurityAppProperties props;

	@Autowired
	public UserTokenAuthService(
			@Qualifier("userTokenPostProcessor")
					TokenAuthenticationProcessor processor,
			SecurityAppProperties securityProperties
	) {
		this.props = securityProperties;
		this.processor = processor;
	}

	@Override
	protected String getTokenSecret() {
		return props.getJwtTokenSecret();
	}

	@Override
	protected Long getExpirationTime() {
		return props.getTokenExpirationTime();
	}

	@Override
	protected String getDefaultRole() {
		return props.getDefaultRole();
	}

	@Override
	protected String getTokenHeader() {
		return props.getJwtTokenHeader();
	}

	@Override
	protected String getTokenPrefix() {
		return props.getJwtTokenPrefix();
	}

	@Override
	protected TokenAuthenticationProcessor getProcessor() {
		return processor;
	}
}