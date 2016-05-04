package es.uniovi.asw.dbupdate.ports;

import es.uniovi.asw.dbupdate.RegisterVote;
import es.uniovi.asw.dbupdate.ports.verifiers.*;
import es.uniovi.asw.dbupdate.repositories.*;
import es.uniovi.asw.model.*;
import es.uniovi.asw.util.ParametersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RegisterVoteP Created by ivan on 2/04/16.
 */
@Service
public class RegisterVoteP implements RegisterVote {

	@Autowired
	private CandidatureRepository candidatureRepository;

	@Autowired
	private VotingPlaceRepository placeRepository;

	@Autowired
	private VoterRepository voterRepository;

	@Autowired
	private VoteRepository voteRepository;

	@Override
	public Vote insertVote(Long idCandidature, Long idVotingPlace) throws ParametersException {

		if (idCandidature == null) {
			throw new ParametersException("El id de candidatura es nulo");
		}

		if (idVotingPlace == null) {
			throw new ParametersException("El id de colegio electoral es nulo");
		}

		VoteVerifier.verify(idCandidature, idVotingPlace);

		VotingPlace votingPlace = placeRepository.findOne(idVotingPlace);
		VotingPlaceVerifier.verify(votingPlace);

		Candidature candidature = candidatureRepository.findOne(idCandidature);
		CandidatureVerifier.verify(candidature);

		Vote vote = new Vote(votingPlace, candidature);
		return voteRepository.save(vote);
	}

	@Override
	public Voter registerVoter(Long id, Long idElection) throws ParametersException {

		if (id == null || idElection == null) {
			throw new ParametersException("Los id de votante y elección no pueder ser nulos");
		}

		Voter voter = voterRepository.findOne(id);
		VoterVerifier.verify(voter);

		for (VotedElection votedElection : voter.getVotedElections()) {
			if (votedElection.getIdElection() == idElection)
				throw new ParametersException("El voto del votante ya ha sido registrado");
		}

		VotedElection votedElection = new VotedElection(idElection);

		voter.addVotedElection(votedElection);
		return voterRepository.save(voter);
	}

}
