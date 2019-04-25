package dev.tindersamurai.blinckserver.mvc.model.dao.infrastructure;

import dev.tindersamurai.blinckserver.mvc.model.entity.infrastructure.UpdateNotification;
import dev.tindersamurai.blinckserver.util.dao.BlinckDaoTemplate;

import java.util.List;

/**
 * @author Henry on 06/09/17.
 */
public interface UpdateNotificationDao extends BlinckDaoTemplate<UpdateNotification, Long> {

	List<UpdateNotification> getAllNotificationsByUserIdOrderDesc(Long userId, int page, int size);
	List<UpdateNotification> getAllNotificationsByUserIdOrderDesc(Long userId);
	long countUserNotifications(Long userId);
	void removeUserNotifications(Long userId);
}