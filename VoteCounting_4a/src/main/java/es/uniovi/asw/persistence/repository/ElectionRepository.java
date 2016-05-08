package es.uniovi.asw.persistence.repository;

import es.uniovi.asw.model.Election;
import es.uniovi.asw.model.ElectionCall;
import es.uniovi.asw.model.types.ElectionDateTime;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * ElectionRepository Created by ivan on 1/04/16.
 */
public interface ElectionRepository extends CrudRepository<Election, Long> {

	Election findByName(String name);

	Iterable<Election> findByElectionCall(ElectionCall electionCall);

	Election findByElectionDateTime(ElectionDateTime electionDateTime);

}
