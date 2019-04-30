package dev.tindersamurai.blinckserver.mvc.service.chat;

import dev.tindersamurai.blinckserver.mvc.data.dao.jpa.chat.ChatMessageRepo;
import dev.tindersamurai.blinckserver.mvc.data.dao.jpa.chat.ChatRepo;
import dev.tindersamurai.blinckserver.mvc.data.entity.chat.ChatMessage;
import dev.tindersamurai.blinckserver.util.OffsetBasedPageRequest;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service @Slf4j
public class ChatService implements IChatService {

	private final ChatMessageRepo chatMessageRepo;
	private final ChatRepo chatRepo;

	@Autowired
	public ChatService(ChatMessageRepo chatMessageRepo, ChatRepo chatRepo) {
		this.chatMessageRepo = chatMessageRepo;
		this.chatRepo = chatRepo;
	}

	private static String createExceptionMsg1(SendMessage message) {
		return "User " + message.getAuthorId() + "doesn't belong to this chat: " + message.getChatId();
	}



	@Override @Transactional
	public void sendMessageToChat(SendMessage message) {

		val chat = chatRepo.getOne(message.getChatId());
		val user = chat.getUsers().stream().filter(u -> u.getId().equals(message.getAuthorId())).findFirst();
		if (!user.isPresent()) throw new AccessDeniedException(createExceptionMsg1(message));

		val msg = new ChatMessage(); {
			msg.setDate(Calendar.getInstance().getTime());
			msg.setChat(chat);
			msg.setAuthor(user.get());
			msg.setText(message.getMessage());
		}
		chatMessageRepo.save(msg);
	}

	@Override @Transactional
	public List<Message> getChatMessages(Long userId, Long chatId, int skipN, int numb) {

		val chat = chatRepo.getOne(chatId);
		val user = chat.getUsers().stream().filter(u -> u.getId().equals(userId)).findFirst();
		if (!user.isPresent()) throw new AccessDeniedException("User " + userId + "doesn't belong to this chat: " + chatId);

		@Cleanup val stream = chatMessageRepo.streamAllByChatOrderByDateDesc(chat, OffsetBasedPageRequest.of(skipN, numb));
		return stream.map(m -> new Message(
				m.getId(),
				m.getChat().getId(),
				m.getAuthor().getId(),
				m.getDate(),
				m.getText()
		)).collect(Collectors.toList());
	}

}