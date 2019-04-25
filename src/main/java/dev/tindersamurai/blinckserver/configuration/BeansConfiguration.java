package dev.tindersamurai.blinckserver.configuration;

import dev.tindersamurai.blinckserver.security.details.DetailsServiceBeans;
import dev.tindersamurai.blinckserver.security.token.processor.TokenProcessorBeans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Henry on 30/08/17.
 */
@Configuration
@Import({
		DetailsServiceBeans.class,
		TokenProcessorBeans.class
}) public abstract class BeansConfiguration { }