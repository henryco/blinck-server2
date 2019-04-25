package dev.tindersamurai.blinckserver.mvc.model.dao.relation.conversation.imp;

import dev.tindersamurai.blinckserver.mvc.model.dao.relation.conversation.PartyConversationDao;
import dev.tindersamurai.blinckserver.mvc.model.entity.relation.conversation.PartyConversation;
import dev.tindersamurai.blinckserver.mvc.model.repository.relation.conversation.PartyConversationRepository;
import dev.tindersamurai.blinckserver.util.dao.repo.BlinckRepositoryProvider;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Henry on 29/08/17.
 */
@Repository
public class PartyConversationDaoImp
		extends BlinckRepositoryProvider<PartyConversation, Long>
		implements PartyConversationDao {

	public PartyConversationDaoImp(PartyConversationRepository repository) {
		super(repository);
	}

	private PartyConversationRepository getRepository() {
		return provideRepository();
	}

	@Override @Transactional
	public List<PartyConversation> getLastByParty(Long partyId, int page, int size) {
		return getRepository().getAllByPartyOrderByMessagePart_DateDesc(partyId, new PageRequest(page, size));
	}

	@Override @Transactional
	public long countByParty(Long partyId) {
		return getRepository().countAllByParty(partyId);
	}

	@Override @Transactional
	public void deleteAllForParty(Long partyId) {
		getRepository().deleteAllByParty(partyId);
	}
}