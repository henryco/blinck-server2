package dev.tindersamurai.blinckserver.mvc.model.dao.relation.core;

import dev.tindersamurai.blinckserver.mvc.model.entity.relation.core.Party;
import dev.tindersamurai.blinckserver.util.dao.BlinckDaoTemplate;

/**
 * @author Henry on 29/08/17.
 */
public interface PartyDao extends BlinckDaoTemplate<Party, Long> {

	Party getRandomFirstInQueue(String typeWanted, String typeIdent, Integer dimension);
	Boolean isPartyActive(Long partyId);

}