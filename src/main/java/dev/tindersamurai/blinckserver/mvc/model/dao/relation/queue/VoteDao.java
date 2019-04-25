package dev.tindersamurai.blinckserver.mvc.model.dao.relation.queue;

import dev.tindersamurai.blinckserver.mvc.model.entity.relation.queue.Vote;
import dev.tindersamurai.blinckserver.util.dao.BlinckDaoTemplate;

import java.util.List;


/**
 * @author Henry on 19/09/17.
 */
public interface VoteDao extends BlinckDaoTemplate<Vote, Long> {


	List<Vote> getAllByTopic(String topic);
	boolean existsByTopicAndVoter(String topic, String voter);
	long countForTopic(String topic);
	long countForTopic(String topic, Boolean voice);
	void deleteAllByTopic(String topic);

}