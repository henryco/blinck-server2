package dev.tindersamurai.blinckserver.mvc.data.dao.custom.session;

import dev.tindersamurai.blinckserver.mvc.data.entity.session.SessionWhiteList;

import java.util.Collection;

public interface SessionWhiteListDao {

	boolean isAnyUserSessionsExists(Long userId);

	void removeAllUserSessions(Long userId);

	boolean isUserSessionExists(Long userId, String token);

	boolean isSessionExists(String token);

	void removeSession(String token);

	Collection<SessionWhiteList> getAll();

	void save(SessionWhiteList session);
}