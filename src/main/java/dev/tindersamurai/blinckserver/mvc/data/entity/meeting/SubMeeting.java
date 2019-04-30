package dev.tindersamurai.blinckserver.mvc.data.entity.meeting;

import dev.tindersamurai.blinckserver.mvc.data.entity.chat.Chat;
import lombok.Data;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.TemporalType.*;

@Data @Entity
public class SubMeeting {

	private @Id @GeneratedValue(
			strategy = AUTO
	) Long id;

	private @JoinColumn(
			name = "host_meeting_id"
	) @ManyToOne(
			fetch = LAZY
	) Meeting host;

	private @JoinColumn(
			name = "chat_id"
	) @OneToOne(
			fetch = LAZY
	) Chat chat;

	private @Column(
			name = "created",
			nullable = false,
			updatable = false
	) @Temporal(
			value = DATE
	) Date created;
}