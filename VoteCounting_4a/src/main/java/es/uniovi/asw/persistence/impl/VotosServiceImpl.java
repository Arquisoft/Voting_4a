package es.uniovi.asw.persistence.impl;

import java.util.List;

import es.uniovi.asw.model.Vote;
import es.uniovi.asw.model.VotingPlace;
import es.uniovi.asw.persistence.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.persistence.VotosService;

@Service
public class VotosServiceImpl implements VotosService {
	
	@Autowired
	private VoteRepository repo;
	

	@Override
	public List<Vote> getAllVotes(VotingPlace ce) {
		return repo.findByVotingPlace(ce);
	}

	@Override
	public void updateVotes(Vote v) {
		repo.save(v);
	}

	@Override
	public List<Vote> getVotes(boolean leido) {
		return repo.findByRead(leido);
	}

}
