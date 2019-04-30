package dev.tindersamurai.blinckserver.mvc.data.entity.meeting;

import lombok.Data;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.AUTO;

@Data @Entity
public class Meeting {

	private @Id @GeneratedValue(
			strategy = AUTO
	) Long id;

	private @OneToMany(
			mappedBy = "host",
			cascade = ALL
	) List<SubMeeting> subMeetings;

}