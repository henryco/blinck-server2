package dev.tindersamurai.blinckserver.mvc.data.entity.session;

import lombok.Data;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.TemporalType.DATE;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity @Data
public class SessionWhiteList {

	private @Id @GeneratedValue(
			strategy = AUTO
	) Long id;

	private @Column(
			name = "user_id",
			updatable = false,
			nullable = false
	) Long userId;

	private @Column(
			name = "token",
			updatable = false,
			nullable = false,
			unique = true
	) String token;

	private @Column(
			name = "created",
			nullable = false,
			updatable = false
	) @Temporal(
			value = TIMESTAMP
	) Date created;
}