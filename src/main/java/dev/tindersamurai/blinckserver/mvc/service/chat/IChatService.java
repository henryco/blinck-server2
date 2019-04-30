package dev.tindersamurai.blinckserver.mvc.service.chat;

import lombok.Value;

import java.util.Date;
import java.util.List;

public interface IChatService {

	void sendMessageToChat(SendMessage message);

	List<Message> getChatMessages(Long userId, Long chatId, int skipN, int numb);

	@Value class SendMessage {
		private Long chatId;
		private Long authorId;
		private String message;
	}


	@Value class Message  {
		private Long id;
		private Long chatId;
		private Long authorId;
		private Date timestamp;
		private String message;
	}
}