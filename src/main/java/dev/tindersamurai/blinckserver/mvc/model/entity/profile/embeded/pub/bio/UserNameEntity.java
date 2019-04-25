package dev.tindersamurai.blinckserver.mvc.model.entity.profile.embeded.pub.bio;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Henry on 23/08/17.
 */
@Embeddable
@Data
@NoArgsConstructor
public class UserNameEntity {


	private @Column(
			nullable = false
	) String firstName;


	private @Column
	String secondName;


	private @Column
	String lastName;


	private @Column(
			unique = true
	) String nickname;

}