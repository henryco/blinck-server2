package dev.tindersamurai.blinckserver.properties;

import org.springframework.lang.NonNull;

public interface SecurityAppProperties extends DynamicPropertyProvider {
	@NonNull
	String getDefaultRole();

	@NonNull
	Long getTokenExpirationTime();

	@NonNull
	String getJwtTokenSecret();

	@NonNull
	String getJwtTokenHeader();

	@NonNull
	String getJwtTokenPrefix();
}
