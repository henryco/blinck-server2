package dev.tindersamurai.blinckserver.mvc.data.entity.meeting;

import lombok.Data;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.TemporalType.DATE;

@Data @Entity
public class Meeting {

	private @Id @GeneratedValue(
			strategy = AUTO
	) Long id;

	private @OneToMany(
			mappedBy = "host"
	) List<SubMeeting> subMeetings;

	private @Column(
			name = "created",
			nullable = false,
			updatable = false
	) @Temporal(
			value = DATE
	) Date created;
}