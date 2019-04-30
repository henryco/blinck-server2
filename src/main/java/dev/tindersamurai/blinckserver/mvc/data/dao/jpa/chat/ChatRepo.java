package dev.tindersamurai.blinckserver.mvc.data.dao.jpa.chat;

import dev.tindersamurai.blinckserver.mvc.data.entity.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepo extends JpaRepository<Chat, Long> {

}