package dev.tindersamurai.blinckserver.mvc.data.entity.meeting;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

@Data @Entity
public class SubMeeting {

	private @Id @GeneratedValue(
			strategy = AUTO
	) Long id;


	private @JoinColumn(
			name = "host_meeting_id"
	) @ManyToOne(
			fetch = LAZY,
			cascade = ALL
	) Meeting host;

}