package dev.tindersamurai.blinckserver.configuration;

import dev.tindersamurai.blinckserver.configuration.spring.WebMvcConfiguration;
import dev.tindersamurai.blinckserver.configuration.spring.WebSecurityConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Henry on 21/08/17.
 */
@Configuration
@Import({
		WebSecurityConfiguration.class,
		WebMvcConfiguration.class,
//		WebSocketConfiguration.class,
//		WSTAuthenticationConfiguration.class,
		BeansConfiguration.class
}) public abstract class MainConfiguration { }