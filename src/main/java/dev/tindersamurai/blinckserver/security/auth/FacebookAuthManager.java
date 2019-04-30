package dev.tindersamurai.blinckserver.security.auth;

import dev.tindersamurai.blinckserver.mvc.service.social.IFacebookProfileService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.stereotype.Component;

import java.util.Collections;


/**
 * @author Henry on 23/08/17.
 */
@Component @Slf4j
public class FacebookAuthManager implements AuthenticationManager {

	private static final String[] FACEBOOK_PERMISSIONS = {
			"id", "name", "birthday", "gender", "first_name", "about",
			"last_name", "middle_name", "locale", "location", "email"
	};

	private @Value("blinck.facebook.app.secret") String app_secret;
	private @Value("blinck.facebook.app.id") String app_id;

	private final IFacebookProfileService facebookProfileService;
	private final UserDetailsService detailsService;


	@Autowired
	public FacebookAuthManager(
			@Qualifier("blinckDetailsService")
					UserDetailsService detailsService,
			IFacebookProfileService facebookProfileService
	) {
		this.facebookProfileService = facebookProfileService;
		this.detailsService = detailsService;
	}


	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		val facebook_uid = authentication.getPrincipal();
		val facebook_token = authentication.getCredentials();

		val factory = new FacebookConnectionFactory(app_id, app_secret);
		Connection<Facebook> connection = factory.createConnection(new AccessGrant(facebook_token.toString()));
		val facebook = connection.getApi();
		checkFacebook(facebook);


		val userDetails = loadDetails(facebook, facebook_uid.toString());
		checkDetails(userDetails);

		return new UsernamePasswordAuthenticationToken(
				userDetails.getUsername(), null,
				Collections.emptyList()
		);
	}



	private UserDetails loadDetails(Facebook facebook, String uid) {

		val userProfile = facebook.fetchObject("me", User.class, FACEBOOK_PERMISSIONS);
		checkProfile(userProfile, uid);

		try {
			val userId = facebookProfileService.getUserIdFromFacebook(userProfile);
			log.info("FOUND USER BY FB_ID: {}", userId);
			return detailsService.loadUserByUsername(Long.toString(userId));
		} catch (UsernameNotFoundException e) {
			val id = facebookProfileService.addNewFacebookUser(facebook, userProfile);
			return detailsService.loadUserByUsername(Long.toString(id));
		}
	}



	private static
	boolean primaryCheck(UserDetails userDetails) {

		return userDetails.isEnabled()
				&& userDetails.isAccountNonExpired()
				&& userDetails.isAccountNonLocked()
		&& userDetails.isCredentialsNonExpired();
	}


	private static
	void checkDetails(UserDetails userDetails)
			throws InsufficientAuthenticationException {
		if (!primaryCheck(userDetails))
			throw new InsufficientAuthenticationException("Account is disabled");
	}


	private static
	void checkProfile(User userProfile, Object facebook_uid)
			throws BadCredentialsException {
		if (!userProfile.getId().equals(facebook_uid.toString()))
			throw new BadCredentialsException("Invalid user id or token");
	}


	private static
	void checkFacebook(Facebook facebook)
			throws SessionAuthenticationException {
		if (!facebook.isAuthorized())
			throw new SessionAuthenticationException("FACEBOOK UNAUTHORIZED");
	}


}