package dev.tindersamurai.blinckserver.security.token.processor;

import dev.tindersamurai.blinckserver.configuration.BeansConfiguration;
import dev.tindersamurai.blinckserver.mvc.service.session.ISessionWhiteListService;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

/**
 * @author Henry on 02/09/17.
 */
@Configuration
public class TokenProcessorBeans extends BeansConfiguration {
	
	//todo: REPLACING WHITE LIST SESSION FOR THE SAME USER
	
	public @Bean TokenAuthenticationProcessor
	userTokenPostProcessor(ISessionWhiteListService whiteListService) {

		return new TokenAuthenticationProcessor() {

			@Override
			public Authentication processAuthentication(Authentication authentication, String token) {
				val userId = Long.valueOf(authentication.getName());
				if (!whiteListService.isUserTokenInTheWhiteList(userId, token))
					return null;
				return authentication;
			}

			@Override
			public void addAuthentication(Authentication authentication, String token) {
				val userId = Long.valueOf(authentication.getName());
				whiteListService.addUserToWhiteList(userId, token);
			}

		};
	}

}
