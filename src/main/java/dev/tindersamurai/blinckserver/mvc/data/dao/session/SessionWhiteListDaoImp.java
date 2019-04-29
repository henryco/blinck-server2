package dev.tindersamurai.blinckserver.mvc.data.dao.session;

import dev.tindersamurai.blinckserver.mvc.data.entity.session.SessionWhiteList;
import dev.tindersamurai.blinckserver.mvc.data.repository.session.SessionWhiteListRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository @Slf4j
public class SessionWhiteListDaoImp implements SessionWhiteListDao {

	private final SessionWhiteListRepo whiteListRepo;

	@Autowired
	public SessionWhiteListDaoImp(SessionWhiteListRepo whiteListRepo) {
		this.whiteListRepo = whiteListRepo;
	}

	@Override @Transactional
	public boolean isAdminSessionExists(String adminId) {
		return whiteListRepo.existsByAdminId(adminId);
	}

	@Override @Transactional
	public boolean isUserSessionExists(Long userId) {
		return whiteListRepo.existsByUserId(userId);
	}

	@Override @Transactional
	public void removeAdminSession(String adminId) {
		whiteListRepo.deleteAllByAdminId(adminId);
	}

	@Override @Transactional
	public void removeUserSession(Long userId) {
		whiteListRepo.deleteAllByUserId(userId);
	}

	@Override @Transactional
	public Collection<SessionWhiteList> getAll() {
		return whiteListRepo.findAll();
	}

	@Override @Transactional
	public void save(SessionWhiteList session) {
		whiteListRepo.save(session);
	}
}