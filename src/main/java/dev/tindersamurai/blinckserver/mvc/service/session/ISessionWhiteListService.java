package dev.tindersamurai.blinckserver.mvc.service.session;

import dev.tindersamurai.blinckserver.mvc.data.entity.session.SessionWhiteList;

import java.util.Collection;

public interface ISessionWhiteListService {

	void addUserToWhiteList(Long userId, String token);

	boolean isUserTokenInTheWhiteList(Long userId, String token);

	boolean isUserInTheWhiteList(Long userId);

	void removeUserFromWhiteList(Long userId);

	void removeTokenFromWhiteList(String token);

	Collection<SessionWhiteList> getAllSessions();
}
