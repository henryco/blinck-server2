package dev.tindersamurai.blinckserver.mvc.model.dao.relation.conversation;

import dev.tindersamurai.blinckserver.mvc.model.entity.relation.conversation.FriendshipConversation;
import dev.tindersamurai.blinckserver.util.dao.BlinckDaoTemplate;

import java.util.List;

/**
 * @author Henry on 29/08/17.
 */
public interface FriendshipConversationDao extends BlinckDaoTemplate<FriendshipConversation, Long> {


	List<FriendshipConversation> getByFriendshipId(Long id, int page, int size);

	void deleteByFriendshipId(Long id);

	long countByFriendshipId(Long id);
}
