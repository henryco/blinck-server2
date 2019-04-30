package dev.tindersamurai.blinckserver.security.details;

import dev.tindersamurai.blinckserver.mvc.data.dao.jpa.user.UserProfileRepo;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service @Slf4j
public class BlinckDetailsService implements UserDetailsService {

	private final UserProfileRepo userProfileRepo;
	private final String default_role;

	@Autowired
	public BlinckDetailsService(UserProfileRepo userProfileRepo, Environment environment) {
		this.default_role = environment.getProperty("blinck.security.default.role");
		this.userProfileRepo = userProfileRepo;
	}

	@Override @Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			val one = userProfileRepo.getOne(Long.valueOf(username));
			log.info("ONE: {}", one);
			return new BlinckUserDetails(username, one.getAuthProfile().isLocked(), default_role);
		} catch (EntityNotFoundException e) {
			log.error("UserNotFound: " + username, e);
			throw new UsernameNotFoundException(username, e);
		}
	}
}