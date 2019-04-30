package dev.tindersamurai.blinckserver.security.token.service;

import dev.tindersamurai.blinckserver.security.token.processor.TokenAuthenticationProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


/**
 * @author Henry on 22/08/17.
 */

@Service
@Qualifier("UserTokenAuthService")
public final class UserTokenAuthService extends TokenAuthenticationService {

	private final TokenAuthenticationProcessor processor;

	private final String app_secret;
	private final String default_role;
	private final String header_string;
	private final String token_prefix;
	private final Long expiration_time;


	@Autowired
	public UserTokenAuthService(Environment environment,
								@Qualifier("userTokenPostProcessor")
										TokenAuthenticationProcessor processor) {

		expiration_time = Long.decode(environment.getProperty("blinck.security.jwt.expiration", "864000000"));
		app_secret = environment.getProperty("blinck.security.jwt.secret.user");
		default_role = environment.getProperty("blinck.security.default.role");
		header_string = environment.getProperty("blinck.security.jwt.header");
		token_prefix = environment.getProperty("blinck.security.jwt.prefix");

		this.processor = processor;
	}

	@Override
	protected String getTokenSecret() {
		return app_secret;
	}

	@Override
	protected Long getExpirationTime() {
		return expiration_time;
	}

	@Override
	protected String getDefaultRole() {
		return default_role != null ? default_role : super.getDefaultRole();
	}

	@Override
	protected String getTokenHeader() {
		return header_string != null ? header_string : super.getTokenHeader();
	}

	@Override
	protected String getTokenPrefix() {
		return token_prefix != null ? token_prefix : super.getTokenPrefix();
	}


	@Override
	protected TokenAuthenticationProcessor getProcessor() {
		return processor;
	}
}