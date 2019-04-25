package dev.tindersamurai.blinckserver.mvc.model.entity.relation.core.embeded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;

/**
 * @author Henry on 14/09/17.
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Details {

	private @Embedded
	@JoinColumn(
			updatable = false,
			nullable = false,
			name = "typo"
	) Type type;


	private @Column(
			name = "in_queue",
			nullable = false
	) Boolean inQueue;

}
