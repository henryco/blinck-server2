package dev.tindersamurai.blinckserver.mvc.model.entity.profile.embeded.pub;

import lombok.Data;
import lombok.NoArgsConstructor;
import dev.tindersamurai.blinckserver.mvc.model.entity.profile.embeded.pub.bio.BioEntity;
import dev.tindersamurai.blinckserver.mvc.model.entity.profile.embeded.pub.media.MediaEntity;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;

/**
 * @author Henry on 30/08/17.
 */
@Embeddable
@Data
@NoArgsConstructor
public class PublicProfile {


	private @Embedded
	@JoinColumn(
			name = "bio"
	)
	BioEntity bio;


	private @Embedded
	@JoinColumn(
			name = "media"
	)
	MediaEntity media;

}