package dev.tindersamurai.blinckserver.mvc.data.entity.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data @Entity
public final class SocialProfile {

	private @Id @GeneratedValue Long id;

	private @Column(
			name = "facebook_id",
			unique = true
	) String facebookId;

	// TODO MORE
}