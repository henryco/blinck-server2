package dev.tindersamurai.blinckserver.mvc.controller.secured.user.friendship.chat;

import dev.tindersamurai.blinckserver.configuration.project.notification.BlinckNotification;
import dev.tindersamurai.blinckserver.mvc.controller.BlinckController;
import dev.tindersamurai.blinckserver.mvc.service.relation.core.FriendshipService;
import org.springframework.security.access.AccessDeniedException;

/**
 * @author Henry on 11/09/17.
 */
public abstract class FriendshipMessageController implements BlinckController, BlinckNotification {


	protected static void accessCheck(FriendshipService friendshipService, Long friendshipId, Long userId)
			throws AccessDeniedException {
		if (!friendshipService.existsRelationWithUser(friendshipId, userId))
			throw new AccessDeniedException("Wrong user or conversation ID");
	}


}