package dev.tindersamurai.blinckserver.mvc.model.dao.relation.conversation;

import dev.tindersamurai.blinckserver.mvc.model.entity.relation.conversation.SubPartyConversation;
import dev.tindersamurai.blinckserver.util.dao.BlinckDaoTemplate;

import java.util.List;

/**
 * @author Henry on 29/08/17.
 */
public interface SubPartyConversationDao extends BlinckDaoTemplate<SubPartyConversation, Long> {

	List<SubPartyConversation> getLastNBySubPartyId(Long id, int page, int size);
	long countBySubPartyId(Long id);
	void deleteAllBySubPartyId(Long id);

}