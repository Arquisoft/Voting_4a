package es.uniovi.asw.persistence.repository;

import es.uniovi.asw.model.VotedElection;
import org.springframework.data.repository.CrudRepository;

/**
 * VoteRepository
 * Created by ivan on 1/04/16.
 */
public interface VotedElectionRepository extends CrudRepository<VotedElection, Long> {

}
