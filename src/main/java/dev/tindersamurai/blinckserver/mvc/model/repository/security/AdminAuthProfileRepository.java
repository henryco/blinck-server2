package dev.tindersamurai.blinckserver.mvc.model.repository.security;

import dev.tindersamurai.blinckserver.mvc.model.entity.security.AdminAuthProfile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Henry on 30/08/17.
 */
public interface AdminAuthProfileRepository extends JpaRepository<AdminAuthProfile, String> {

}
