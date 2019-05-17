package dev.tindersamurai.blinckserver.properties.imp;

import dev.tindersamurai.blinckserver.properties.FacebookAppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class FacebookAppPropertiesImp implements FacebookAppProperties {

	private final Environment environment;

	@Autowired
	public FacebookAppPropertiesImp(Environment environment) {
		this.environment = environment;
	}

	@Override
	public Environment provideEnv() {
		return environment;
	}

	@NonNull @Override
	public String getAppSecret() {
		return get("blinck.facebook.app.secret");
	}

	@NonNull @Override
	public String getAppId() {
		return get("blinck.facebook.app.id");
	}
}