package es.uniovi.asw.dbupdate.repositories;

import es.uniovi.asw.model.Voter;
import org.springframework.data.repository.CrudRepository;

public interface VoterRepository extends CrudRepository<Voter, Long>{
	
	Voter findByNif(String nif);
	
}
