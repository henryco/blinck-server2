package dev.tindersamurai.blinckserver.mvc.model.dao.relation.core;

import dev.tindersamurai.blinckserver.mvc.model.entity.relation.core.SubPartyQueue;
import dev.tindersamurai.blinckserver.util.dao.BlinckDaoTemplate;

import java.util.List;

/**
 * @author Henry on 15/09/17.
 */
public interface SubPartyQueueDao extends BlinckDaoTemplate<SubPartyQueue, Long> {

	void deleteAllByOwnerId(Long id);
	List<SubPartyQueue> getAllWithUser(Long user);
}