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
	public boolean isAnyUserSessionsExists(Long userId) {
		return whiteListRepo.existsByUserId(userId);
	}

	@Override @Transactional
	public void removeAllUserSessions(Long userId) {
		whiteListRepo.deleteAllByUserId(userId);
	}

	@Override @Transactional
	public boolean isUserSessionExists(Long userId, String token) {
		return whiteListRepo.existsByUserIdAndToken(userId, token);
	}

	@Override @Transactional
	public boolean isSessionExists(String token) {
		return whiteListRepo.existsByToken(token);
	}

	@Override @Transactional
	public void removeSession(String token) {
		whiteListRepo.deleteAllByToken(token);
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