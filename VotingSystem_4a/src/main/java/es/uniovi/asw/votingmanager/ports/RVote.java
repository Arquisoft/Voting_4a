package es.uniovi.asw.votingmanager.ports;

import es.uniovi.asw.util.ParametersException;
import es.uniovi.asw.votingmanager.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RVote
 * Created by ivan on 2/04/16.
 */
@Service
public class RVote implements Vote {

	@Autowired
	private RegisterVoteR registerVoteR;

	@Override
	public void registerVote(Long idVoter, Long idVotingPlace, Long idCandidature) throws ParametersException {

		if (idVoter == null || idVotingPlace == null || idCandidature == null) {
			throw new ParametersException("Los datos del voto no pueden ser nulos");
		}

		registerVoteR.vote(idCandidature, idVotingPlace);
		registerVoteR.markVoted(idVoter);
	}

}
