package dev.tindersamurai.blinckserver.mvc.model.repository.security;

import dev.tindersamurai.blinckserver.mvc.model.entity.security.AdminVerificationQueue;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Henry on 31/08/17.
 */
public interface AdminVerificationQueueRepository extends JpaRepository<AdminVerificationQueue, String> {

}