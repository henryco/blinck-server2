package dev.tindersamurai.blinckserver.mvc.data.repository.session;

import dev.tindersamurai.blinckserver.mvc.data.entity.session.SessionWhiteList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionWhiteListRepo extends JpaRepository<SessionWhiteList, Long> {

	boolean existsByUserIdAndToken(Long userId, String token);

	boolean existsByUserId(Long userId);

	boolean existsByToken(String token);

	void deleteAllByUserId(Long userId);

	void deleteAllByToken(String token);
}