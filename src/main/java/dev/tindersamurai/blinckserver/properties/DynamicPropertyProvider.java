package dev.tindersamurai.blinckserver.properties;

import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;

public interface DynamicPropertyProvider {

	Environment provideEnv();

	default String get(@NonNull String name) {
		return provideEnv().getRequiredProperty(name);
	}

	default String get(@NonNull String name, @NonNull String def) {
		return provideEnv().getProperty(name, def);
	}
}