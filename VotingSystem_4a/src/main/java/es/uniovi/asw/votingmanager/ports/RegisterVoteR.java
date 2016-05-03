package es.uniovi.asw.votingmanager.ports;

import es.uniovi.asw.dbupdate.ports.RegisterVoteP;
import es.uniovi.asw.util.ParametersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RegisterVoteR Created by ivan on 2/04/16.
 */
@Service
public class RegisterVoteR {

	@Autowired
	private RegisterVoteP registerVoteP;

	public void vote(Long idCandidature, Long idVotingPlace) throws ParametersException {
		registerVoteP.insertVote(idCandidature, idVotingPlace);
	}

	public void markVoted(Long idVoter) throws ParametersException {
		registerVoteP.registerVoter(idVoter);
	}

}
