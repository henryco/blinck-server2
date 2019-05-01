package dev.tindersamurai.blinckserver.configuration.spring;

import dev.tindersamurai.blinckserver.security.auth.FacebookAuthManager;
import dev.tindersamurai.blinckserver.security.credentials.FacebookCredentials;
import dev.tindersamurai.blinckserver.security.filter.ResetFilter;
import dev.tindersamurai.blinckserver.security.filter.jwt.JWTAuthFilter;
import dev.tindersamurai.blinckserver.security.filter.jwt.JWTLoginFilter;
import dev.tindersamurai.blinckserver.security.token.service.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static dev.tindersamurai.blinckserver.configuration.spring.WebMvcConfiguration.DATA_PATH_URL;
import static org.springframework.http.HttpMethod.GET;

/**
 * @author Henry on 21/08/17.
 */

@SuppressWarnings("UnusedReturnValue")
@Configuration
@ComponentScan(basePackageClasses = {
		FacebookAuthManager.class
}) public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final TokenAuthenticationService userTokenAuthService;

	private final UserDetailsService userDetailsService;
	private final AuthenticationManager facebookAuthManager;


	@Autowired
	public WebSecurityConfiguration(
			@Qualifier("blinckDetailsService") UserDetailsService userDetailsService,
			@Qualifier("facebookAuthManager") AuthenticationManager facebookAuthManager,
			@Qualifier("UserTokenAuthService") TokenAuthenticationService userTokenAuthService
	) {
		this.userDetailsService = userDetailsService;
		this.facebookAuthManager = facebookAuthManager;
		this.userTokenAuthService = userTokenAuthService;
	}



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		HttpSecurity httpSecurity = http.csrf().disable();
		httpSecurity = authorizeRequests(httpSecurity);
		httpSecurity = filterUserLoginRequest(httpSecurity);
		filterJwtHeader(httpSecurity);
	}





	private HttpSecurity authorizeRequests(HttpSecurity http) throws Exception {
		return http.authorizeRequests()
				.antMatchers("/", "/rest/public/**", "/login/**").permitAll()
				.antMatchers(GET, "/session/**", "/health", DATA_PATH_URL + "**").permitAll()
				.antMatchers("/actuator/health").permitAll()
				.anyRequest().authenticated()
		.and();
	}


	private HttpSecurity filterUserLoginRequest(HttpSecurity http) {
		return http.addFilterBefore( // We filter the api/ USER login requests (if user will created if not exists)
				new JWTLoginFilter(
						"/login/fb/**",
						facebookAuthManager,
						userTokenAuthService,
						FacebookCredentials.class
				), UsernamePasswordAuthenticationFilter.class
		);
	}

	private HttpSecurity filterJwtHeader(HttpSecurity http) {
		return http
				.addFilterBefore(
						new ResetFilter(),
						UsernamePasswordAuthenticationFilter.class
				)
				.addFilterBefore(
						new JWTAuthFilter(userTokenAuthService),
						UsernamePasswordAuthenticationFilter.class
				)
		;
	}


}