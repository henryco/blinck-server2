package dev.tindersamurai.blinckserver.mvc.model.dao.relation.queue;

import dev.tindersamurai.blinckserver.mvc.model.entity.relation.queue.FriendshipNotification;
import dev.tindersamurai.blinckserver.util.dao.BlinckDaoTemplate;

import java.util.List;

/**
 * @author Henry on 04/09/17.
 */
public interface FriendshipNotificationDao extends BlinckDaoTemplate<FriendshipNotification, Long> {

	List<FriendshipNotification> getAllByReceiverId(Long receiverId, int page, int size);
	List<FriendshipNotification> getAllByInitiatorId(Long initiatorId, int page, int size);
	List<FriendshipNotification> getAllByUserId(Long id, int page, int size);

	FriendshipNotification getByReceiverAndInitiator(Long receiverId, Long initiatorId);

	boolean existsBetweenReceiverAndInitiator(Long receiverId, Long initiatorId);

	void removeAllByReceiverAndInitiatorId(Long receiverId, Long initiatorId);
	void removeAllByReceiverId(Long receiverId);
	void removeAllByInitiatorId(Long initiatorId);
	void removeAllByUserId(Long id);
}