package es.uniovi.asw.persistence.repository;

import es.uniovi.asw.model.Vote;
import es.uniovi.asw.model.VotingPlace;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * VoteRepository Created by ivan on 1/04/16.
 */
public interface VoteRepository extends CrudRepository<Vote, Long> {

	List<Vote> findByVotingPlace(VotingPlace votingPlace);

	List<Vote> findByRead(boolean read);

}
