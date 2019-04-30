package dev.tindersamurai.blinckserver.mvc.data.entity.session;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

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
}