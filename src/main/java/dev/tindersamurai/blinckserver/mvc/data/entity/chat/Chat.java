package dev.tindersamurai.blinckserver.mvc.data.entity.chat;

import dev.tindersamurai.blinckserver.mvc.data.entity.user.UserProfile;
import lombok.Data;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Data @Entity
public class Chat {

	private @Id @GeneratedValue(
			strategy = AUTO
	) Long id;

	private @Column(
			name = "name"
	) String name;

	private @ManyToMany
	List<UserProfile> users;

}