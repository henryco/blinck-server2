package dev.tindersamurai.blinckserver.mvc.service.social;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;

public interface IFacebookProfileService {

	/**
	 * @param facebook see {@link Facebook}
	 * @param user see {@link User}
	 * @return ID from created user profile
	 */
	Long addNewFacebookUser(Facebook facebook, User user);

	/**
	 * @param user see {@link User}
	 * @return UserId from user related to facebook account
	 * @throws UsernameNotFoundException when cannot resolve user
	 */
	Long getUserIdFromFacebook(User user) throws UsernameNotFoundException;
}
