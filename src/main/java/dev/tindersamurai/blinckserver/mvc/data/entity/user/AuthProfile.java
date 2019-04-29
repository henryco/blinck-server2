package dev.tindersamurai.blinckserver.mvc.data.entity.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data @Entity
public final class AuthProfile {

	private @Id @GeneratedValue Long id;

	private @Column(
			name = "locked",
			nullable = false
	) boolean locked;
}