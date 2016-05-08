package es.uniovi.asw.persistence.repository;

import es.uniovi.asw.model.District;
import es.uniovi.asw.model.Region;
import org.springframework.data.repository.CrudRepository;

/**
 * DistrictRepository Created by ivan on 1/04/16.
 */
public interface DistrictRepository extends CrudRepository<District, Long> {

	District findByName(String name);

	Iterable<District> findByRegion(Region region);

}
