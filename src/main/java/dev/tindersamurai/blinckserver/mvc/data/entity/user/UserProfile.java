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

	private @Embedded PrivateProfile privateProfile;
	private @Embedded PublicProfile publicProfile;
	private @Embedded MediaProfile mediaProfile;

	private @JoinColumn(
			name = "social_id"
	) @OneToOne(
			cascade = ALL
	) SocialProfile socialProfile;

	private @JoinColumn(
			name = "auth_id"
	) @OneToOne(
			cascade = ALL
	) AuthProfile authProfile;

}