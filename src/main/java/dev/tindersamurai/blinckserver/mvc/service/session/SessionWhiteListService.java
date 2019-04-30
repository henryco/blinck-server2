package dev.tindersamurai.blinckserver.mvc.service.session;

import dev.tindersamurai.blinckserver.mvc.data.dao.custom.session.SessionWhiteListDao;
import dev.tindersamurai.blinckserver.mvc.data.entity.session.SessionWhiteList;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Henry on 01/09/17.
 */
@Service @Slf4j
public class SessionWhiteListService implements ISessionWhiteListService {

	private final SessionWhiteListDao whiteListDao;

	@Autowired
	public SessionWhiteListService(SessionWhiteListDao whiteListDao) {
		this.whiteListDao = whiteListDao;
	}

	@Override
	public void addUserToWhiteList(Long userId, String token) {
		if (!isUserInTheWhiteList(userId)) {
			val session = new SessionWhiteList(); {
				session.setUserId(userId);
				session.setToken(token);
			}
			whiteListDao.save(session);
		}
	}

	@Override
	public void removeUserFromWhiteList(Long userId) {
		if (isUserInTheWhiteList(userId))
			whiteListDao.removeAllUserSessions(userId);
	}

	@Override
	public void removeTokenFromWhiteList(String token) {
		whiteListDao.removeSession(token);
	}

	@Override
	public boolean isUserInTheWhiteList(Long userId) {
		return whiteListDao.isAnyUserSessionsExists(userId);
	}

	@Override
	public boolean isUserTokenInTheWhiteList(Long userId, String token) {
		return whiteListDao.isUserSessionExists(userId, token);
	}

	@Override
	public Collection<SessionWhiteList> getAllSessions() {
		return whiteListDao.getAll();
	}
}