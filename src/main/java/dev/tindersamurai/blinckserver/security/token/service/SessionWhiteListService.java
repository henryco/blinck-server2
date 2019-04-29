package dev.tindersamurai.blinckserver.security.token.service;

import dev.tindersamurai.blinckserver.mvc.data.dao.session.SessionWhiteListDao;
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
public class SessionWhiteListService {

	private final SessionWhiteListDao whiteListDao;

	@Autowired
	public SessionWhiteListService(SessionWhiteListDao whiteListDao) {
		this.whiteListDao = whiteListDao;
	}

	public void addUserToWhiteList(Long userId) {
		if (!isUserInTheWhiteList(userId)) {
			val session = new SessionWhiteList();
			session.setUserId(userId);
			whiteListDao.save(session);
		}
	}

	public void addAdminToWhiteList(String adminId) {
		if (!isAdminInTheWhiteList(adminId)) {
			val session = new SessionWhiteList();
			session.setAdminId(adminId);
			whiteListDao.save(session);
		}
	}

	public void removeUserFromWhiteList(Long userId) {
		if (isUserInTheWhiteList(userId))
			whiteListDao.removeUserSession(userId);
	}

	public void removeAdminFromWhiteList(String adminId) {
		if (isAdminInTheWhiteList(adminId))
			whiteListDao.removeAdminSession(adminId);
	}

	public boolean isUserInTheWhiteList(Long userId) {
		return whiteListDao.isUserSessionExists(userId);
	}

	public boolean isAdminInTheWhiteList(String adminId) {
		return whiteListDao.isAdminSessionExists(adminId);
	}

	public Collection<SessionWhiteList> getAllSessions() {
		return whiteListDao.getAll();
	}
}