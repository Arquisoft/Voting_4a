package es.uniovi.asw.persistence.impl;

import es.uniovi.asw.model.Election;
import es.uniovi.asw.persistence.repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.persistence.VotacionesService;

import java.util.ArrayList;
import java.util.List;

@Service
public class VotacionesServiceImpl implements VotacionesService {
	
	@Autowired
	private ElectionRepository repo;

	@Override
	public List<Election> getVoteInfo(boolean activa) {

		List<Election> elections = new ArrayList<>();

		for (Election election : repo.findAll()) {
			if (activa && election.isActiva())
				elections.add(election);

			if (!activa && !election.isActiva())
				elections.add(election);
		}

		return elections;
	}

}
