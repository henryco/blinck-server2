package dev.tindersamurai.blinckserver.mvc.data.entity.user;

import lombok.Data;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.*;

@Embeddable @Data
public final class PublicProfile {

	private @Column(
			name = "first_name",
			nullable = false
	) String firstName;

	private @Column(
			name = "last_name",
			nullable = false
	) String lastName;

	private @Column(
			name = "birthday",
			nullable = false
	) @Temporal(
			DATE
	) Date birthday;

	private @Column(
			name = "about",
			length = 512
	) String about;

	private @Column(
			name = "sex",
			nullable = false
	) @Enumerated(
			STRING
	) UserSex sex;

}