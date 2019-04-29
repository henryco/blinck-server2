package dev.tindersamurai.blinckserver.security.auth;

import dev.tindersamurai.blinckserver.mvc.data.entity.user.*;
import dev.tindersamurai.blinckserver.mvc.data.repository.user.UserProfileRepo;
import dev.tindersamurai.blinckserver.util.Utils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static dev.tindersamurai.blinckserver.configuration.spring.WebMvcConfiguration.REL_FILE_PATH;
import static dev.tindersamurai.blinckserver.configuration.spring.WebMvcConfiguration.USER_IMAGE_POSTFIX;

@Service @Slf4j
public class FacebookProfileService {

	private static final String FB_DATE_FORMAT = "MM/dd/yyyy";

	private final UserProfileRepo userProfileRepo;

	@Autowired
	public FacebookProfileService(UserProfileRepo userProfileRepo) {
		this.userProfileRepo = userProfileRepo;
	}

	@Transactional
	public Long addNewFacebookUser(Facebook facebook, User userProfile) {
		if (userProfileRepo.existsBySocialProfile_FacebookId(userProfile.getId()))
			return userProfileRepo.getBySocialProfile_FacebookId(userProfile.getId()).getId();
		return userProfileRepo.save(createNewUser(facebook, userProfile)).getId();
	}

	@Transactional
	public Long getUserIdFromFacebook(User userProfile) throws UsernameNotFoundException {
		val profile = userProfileRepo.getBySocialProfile_FacebookId(userProfile.getId());
		if (profile == null)
			throw new UsernameNotFoundException("Cannot find user by facebook id: " + userProfile.getId());
		return profile.getId();
	}


	private UserProfile createNewUser(Facebook facebook, User user) {
		if (userProfileRepo.existsBySocialProfile_FacebookId(user.getId()))
			throw new RuntimeException("User: ["+user.getId()+"] already exists!");
		return Helper.createUserEntity(facebook, user);
	}


	private static final class Helper {

		private static UserProfile createUserEntity(Facebook facebook, User user) {
			val profile = new UserProfile(); {
				profile.setSocialProfile(createSocialProfile(user));
				profile.setPublicProfile(createPublicProfile(user));
				profile.setPrivateProfile(createPrivateProfile(user));
				profile.setMediaProfile(createMediaProfile(facebook, user));
				profile.setAuthProfile(createAuthProfile());
			}
			return profile;
		}


		private static Date parseFacebookDate(String date) {
			try {
				return new SimpleDateFormat(FB_DATE_FORMAT).parse(date);
			} catch (ParseException | NullPointerException e) {
				log.error("Cannot parse facebook date", e);
				return null;
			}
		}

		private static PublicProfile createPublicProfile(User user) {
			val profile = new PublicProfile(); {
				profile.setAbout("");
				profile.setBirthday(parseFacebookDate(user.getBirthday()));
				profile.setFirstName(user.getFirstName());
				profile.setLastName(user.getLastName());
				profile.setSex(UserSex.valueOf(user.getGender().toUpperCase()));
			}
			return profile;
		}

		private static PrivateProfile createPrivateProfile(User user) {
			val profile = new PrivateProfile(); {
				profile.setEmail(user.getEmail());
			}
			return profile;
		}

		private static AuthProfile createAuthProfile() {
			val profile = new AuthProfile(); {
				profile.setLocked(false);
			}
			return profile;
		}

		private static SocialProfile createSocialProfile(User user) {
			val profile = new SocialProfile(); {
				profile.setFacebookId(user.getId());
				// todo
			}
			return profile;
		}

		private static MediaProfile createMediaProfile(Facebook facebook, User user) {
			val profile = new MediaProfile(); {
				if (facebook == null) return profile;
				try {
					val image = facebook.fetchImage(user.getId(), "picture", ImageType.LARGE);
					val saved = Utils.saveImageFile(image, user.getId(), REL_FILE_PATH + USER_IMAGE_POSTFIX);
					profile.setAvatarId(saved);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return profile;
		}

	}
}
