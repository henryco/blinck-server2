package dev.tindersamurai.blinckserver.mvc.data.entity.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable @Data
public final class PrivateProfile {

	private @Column(
			name = "phone"
	) String phone;

	private @Column(
			name = "email"
	) String email;

}