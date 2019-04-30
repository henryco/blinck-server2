package dev.tindersamurai.blinckserver.mvc.data.entity.chat;

import dev.tindersamurai.blinckserver.mvc.data.entity.user.UserProfile;
import lombok.Data;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.TemporalType.DATE;

@Data @Entity
public class Chat {

	private @Id @GeneratedValue(
			strategy = AUTO
	) Long id;

	private @Column(
			name = "name"
	) String name;

	private @Column(
			name = "created",
			nullable = false,
			updatable = false
	) @Temporal(
			value = DATE
	) Date created;

	private @ManyToMany
	List<UserProfile> users;

}