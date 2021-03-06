package es.uniovi.asw.persistence.repository;

import es.uniovi.asw.model.Candidature;
import es.uniovi.asw.model.District;
import es.uniovi.asw.model.ReferendumOption;
import org.springframework.data.repository.CrudRepository;

/**
 * CandidatureRepository Created by ivan on 2/04/16.
 */
public interface CandidatureRepository extends CrudRepository<Candidature, Long> {

	Iterable<Candidature> findByDistrict(District district);
}
