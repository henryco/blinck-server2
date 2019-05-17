package dev.tindersamurai.blinckserver.properties.imp;

import dev.tindersamurai.blinckserver.properties.SecurityAppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class SecurityAppPropertiesImp implements SecurityAppProperties {

	private final Environment environment;

	@Autowired
	public SecurityAppPropertiesImp(Environment environment) {
		this.environment = environment;
	}

	@Override
	public Environment provideEnv() {
		return this.environment;
	}

	@NonNull @Override
	public String getDefaultRole() {
		return get("blinck.security.default.role");
	}

	@NonNull @Override
	public Long getTokenExpirationTime() {
		return Long.valueOf(get("blinck.security.jwt.expiration", "864000000"));
	}

	@NonNull @Override
	public String getJwtTokenSecret() {
		return get("blinck.security.jwt.secret.user");
	}

	@NonNull @Override
	public String getJwtTokenHeader() {
		return get("blinck.security.jwt.header", HttpHeaders.AUTHORIZATION);
	}

	@NonNull @Override
	public String getJwtTokenPrefix() {
		return get("blinck.security.jwt.prefix", "Bearer");
	}
}