package es.uniovi.asw.dbupdate.repositories;

import es.uniovi.asw.model.Voter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * VoterRepository Created by ivan on 1/04/16.
 */
@Repository
public interface VoterRepository extends CrudRepository<Voter, Long> {

	Voter findByNif(String nif);

	Voter findByEmail(String email);

}
