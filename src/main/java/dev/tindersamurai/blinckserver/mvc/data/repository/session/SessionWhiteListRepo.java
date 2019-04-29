package dev.tindersamurai.blinckserver.mvc.data.repository.session;

import dev.tindersamurai.blinckserver.mvc.data.entity.session.SessionWhiteList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionWhiteListRepo extends JpaRepository<SessionWhiteList, Long> {

	void deleteAllByUserId(Long userId);

	void deleteAllByAdminId(String adminId);

	boolean existsByAdminId(String adminId);

	boolean existsByUserId(Long userId);
}