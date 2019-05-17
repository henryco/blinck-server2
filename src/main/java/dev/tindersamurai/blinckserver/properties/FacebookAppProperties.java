package dev.tindersamurai.blinckserver.properties;

import org.springframework.lang.NonNull;

public interface FacebookAppProperties extends DynamicPropertyProvider {
	@NonNull
	String getAppSecret();

	@NonNull
	String getAppId();
}
