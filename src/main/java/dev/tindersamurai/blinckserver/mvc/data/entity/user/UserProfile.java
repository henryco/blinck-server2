package dev.tindersamurai.blinckserver.mvc.data.entity.user;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;

@Data @Entity
public class UserProfile {

	private @Id @GeneratedValue(
			strategy = AUTO
	) Long id;

	@Embedded private PrivateProfile privateProfile;
	@Embedded private PublicProfile publicProfile;
	@Embedded private MediaProfile mediaProfile;

	@OneToOne(cascade = ALL) @JoinColumn(
			name = "social_id"
	) private SocialProfile socialProfile;

	@OneToOne(cascade = ALL) @JoinColumn(
			name = "auth_id"
	) private AuthProfile authProfile;

}