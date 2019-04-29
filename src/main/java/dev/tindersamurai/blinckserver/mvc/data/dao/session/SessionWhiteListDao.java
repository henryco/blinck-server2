package dev.tindersamurai.blinckserver.mvc.data.dao.session;

import dev.tindersamurai.blinckserver.mvc.data.entity.session.SessionWhiteList;

import java.util.Collection;

public interface SessionWhiteListDao {

	boolean isAdminSessionExists(String adminId);

	boolean isUserSessionExists(Long userId);

	void removeAdminSession(String adminId);

	void removeUserSession(Long userId);

	Collection<SessionWhiteList> getAll();

	void save(SessionWhiteList session);
}