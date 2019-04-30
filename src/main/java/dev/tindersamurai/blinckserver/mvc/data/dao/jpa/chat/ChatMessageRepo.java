package dev.tindersamurai.blinckserver.mvc.data.dao.jpa.chat;

import dev.tindersamurai.blinckserver.mvc.data.entity.chat.Chat;
import dev.tindersamurai.blinckserver.mvc.data.entity.chat.ChatMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface ChatMessageRepo extends JpaRepository<ChatMessage, Long> {

	Stream<ChatMessage> streamAllByChatOrderByDateDesc(Chat chat, Pageable pageable);

}