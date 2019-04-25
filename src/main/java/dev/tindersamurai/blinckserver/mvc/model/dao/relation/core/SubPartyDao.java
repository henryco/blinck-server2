package dev.tindersamurai.blinckserver.mvc.model.dao.relation.core;

import dev.tindersamurai.blinckserver.mvc.model.entity.relation.core.SubParty;
import dev.tindersamurai.blinckserver.util.dao.BlinckDaoTemplate;

import java.util.List;

/**
 * @author Henry on 29/08/17.
 */
public interface SubPartyDao extends BlinckDaoTemplate<SubParty, Long> {

	List<SubParty> getAllWithUserInParty(Long user);
	List<SubParty> getAllWithUserInQueue(Long user);

	List<SubParty> getAllWithUser(Long user);

	List<SubParty> getAllInQueue(String typeWanted, String typeIdent, Integer dimension, int page, int size);

	SubParty getRandomFirstInQueue(String typeWanted, String typeIdent, Integer dimension);

	boolean existsWithUser(Long id, Long userId);
}