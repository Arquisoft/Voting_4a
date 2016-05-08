package es.uniovi.asw.persistence.impl;

import es.uniovi.asw.model.VotingPlace;
import es.uniovi.asw.persistence.repository.VotingPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.persistence.ColegiosService;

@Service
public class ColegiosServiceImpl implements ColegiosService {

	@Autowired
	private VotingPlaceRepository repo;
	

	@Override
	public VotingPlace findById(long id) {
		return repo.findOne(id);
	}

}
