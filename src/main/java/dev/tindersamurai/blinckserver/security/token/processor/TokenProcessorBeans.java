package dev.tindersamurai.blinckserver.security.token.processor;

import dev.tindersamurai.blinckserver.configuration.BeansConfiguration;
import dev.tindersamurai.blinckserver.security.token.service.SessionWhiteListService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

/**
 * @author Henry on 02/09/17.
 */
@Configuration
public class TokenProcessorBeans extends BeansConfiguration {
	
	//todo: REPLACING WHITE LIST SESSION FOR THE SAME USER
	
	public @Bean
	TokenAuthenticationProcessor
	userTokenPostProcessor(SessionWhiteListService whiteListService) {

		return new TokenAuthenticationProcessor() {

			@Override
			public Authentication processAuthentication(Authentication authentication) {
				Long name = Long.decode(authentication.getName());
				if (!whiteListService.isUserInTheWhiteList(name))
					return null;
				return authentication;
			}

			@Override
			public void addAuthentication(Authentication authentication) {
				Long name = Long.decode(authentication.getName());
				whiteListService.addUserToWhiteList(name);
			}

		};
	}


	public @Bean
	TokenAuthenticationProcessor
	adminTokenPostProcessor(SessionWhiteListService whiteListService) {

		return new TokenAuthenticationProcessor() {

			@Override
			public Authentication processAuthentication(Authentication authentication) {
				if (!whiteListService.isAdminInTheWhiteList(authentication.getName()))
					return null;
				return authentication;
			}

			@Override
			public void addAuthentication(Authentication authentication) {
				whiteListService.addAdminToWhiteList(authentication.getName());
			}

		};
	}


}