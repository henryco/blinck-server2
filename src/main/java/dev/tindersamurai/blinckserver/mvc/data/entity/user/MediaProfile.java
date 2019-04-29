package dev.tindersamurai.blinckserver.mvc.data.entity.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable @Data
public final class MediaProfile {

	private @Column(
			name = "avatar_id"
	) String avatarId;

//	TODO OTHER IMAGES

}