package dev.tindersamurai.blinckserver.mvc.controller.secured.user.group.chat;

import dev.tindersamurai.blinckserver.configuration.project.websocket.WebSocketConstants;
import dev.tindersamurai.blinckserver.mvc.controller.BlinckController;
import dev.tindersamurai.blinckserver.mvc.service.relation.conversation.ConversationService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;

import java.util.Date;

import static dev.tindersamurai.blinckserver.mvc.service.relation.conversation.ConversationService.MessageForm;

/**
 * @author Henry on 20/09/17.
 */
public interface BlinckStompChatController
		extends WebSocketConstants, BlinckController {


	WebSocketStatusJson sendMessage(Authentication authentication, MessageForm messageForm);


	default WebSocketStatusJson
	createResponse(MessageForm post, Date date, boolean status) {
		return new WebSocketStatusJson(post.getTopic().toString(), date, status);
	}


	default void
	stompSend(SimpMessagingTemplate template, Long[] users, String destination, Object payload) {
		for (Long user: users)
			template.convertAndSendToUser(user.toString(), destination, payload);
	}

	default MessageForm
	saveMessage(ConversationService service, MessageForm messageForm, Long userId) {
		return new MessageForm(service.sendMessage(messageForm, userId));
	}
}
