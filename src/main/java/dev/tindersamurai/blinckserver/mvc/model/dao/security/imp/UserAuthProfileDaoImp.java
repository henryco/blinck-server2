package dev.tindersamurai.blinckserver.mvc.model.dao.security.imp;

import dev.tindersamurai.blinckserver.mvc.model.dao.security.UserAuthProfileDao;
import dev.tindersamurai.blinckserver.mvc.model.entity.security.UserAuthProfile;
import dev.tindersamurai.blinckserver.mvc.model.repository.security.UserAuthProfileRepository;
import dev.tindersamurai.blinckserver.util.dao.repo.BlinckRepositoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Henry on 23/08/17.
 */
@Repository
public class UserAuthProfileDaoImp
		extends BlinckRepositoryProvider<UserAuthProfile, Long>
		implements UserAuthProfileDao {


	@Autowired
	public UserAuthProfileDaoImp(UserAuthProfileRepository profileRepository) {
		super(profileRepository, false);
	}

	private UserAuthProfileRepository getRepository() {
		return provideRepository();
	}

	@Override
	public List<UserAuthProfile> getLockedProfiles(int page, int size) {
		return getRepository().getAllByLockedIsTrue(new PageRequest(page, size));
	}

	@Override
	public long countLockedProfiles() {
		return getRepository().countAllByLockedIsTrue();
	}
}
