package dev.tindersamurai.blinckserver.mvc.model.dao.infrastructure.imp;

import dev.tindersamurai.blinckserver.mvc.model.dao.infrastructure.BlackListDao;
import dev.tindersamurai.blinckserver.mvc.model.entity.infrastructure.BlackList;
import dev.tindersamurai.blinckserver.mvc.model.repository.infrastructure.BlackListRepository;
import dev.tindersamurai.blinckserver.util.dao.repo.BlinckRepositoryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Henry on 29/08/17.
 */
@Repository
public class BlackListDaoImp
		extends BlinckRepositoryProvider<BlackList, Long>
		implements BlackListDao {

	@Autowired
	public BlackListDaoImp(BlackListRepository blackListRepository) {
		super(blackListRepository);
	}

}