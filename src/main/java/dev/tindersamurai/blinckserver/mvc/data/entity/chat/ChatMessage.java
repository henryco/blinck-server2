package dev.tindersamurai.blinckserver.mvc.data.entity.chat;

import dev.tindersamurai.blinckserver.mvc.data.entity.user.UserProfile;
import lombok.Data;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.TemporalType.TIMESTAMP;

@Data @Entity
public class ChatMessage {

	private @Id @GeneratedValue(
			strategy = AUTO
	) Long id;

	private @Column(
			name = "text",
			nullable = false
	) String text;

	private @Column(
			name = "timestamp",
			nullable = false
	) @Temporal(
			TIMESTAMP
	) Date date;

	private @ManyToOne(
			cascade = ALL
	) @JoinColumn(
			name = "chat_id"
	) Chat chat;

	private @ManyToOne(
			cascade = ALL
	) @JoinColumn(
			name = "author_id"
	) UserProfile author;
}