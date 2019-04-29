package dev.tindersamurai.blinckserver.mvc.data.repository.user;

import dev.tindersamurai.blinckserver.mvc.data.entity.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {

	UserProfile getBySocialProfile_FacebookId(String socialProfile_facebookId);

	boolean existsBySocialProfile_FacebookId(String socialProfile_facebookId);
}