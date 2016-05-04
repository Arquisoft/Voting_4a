package es.uniovi.asw.votingmanager.ports;

import es.uniovi.asw.model.VotedElection;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.util.ParametersException;
import es.uniovi.asw.votingmanager.CheckVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RCheckVoter Created by ivan on 2/04/16.
 */
@Service
public class RCheckVoter implements CheckVoter {

	@Autowired
	private GetVoterR getVoterR;

	@Override
	public boolean hasVoted(Long id, Long idElection) throws ParametersException {
		Voter voter = getVoterR.getVoter(id);

		for (VotedElection votedElection : voter.getVotedElections()) {
			if (votedElection.getIdElection() == idElection)
				return true;
		}

		return false;
	}

	@Override
	public boolean hasVoted(String nif, Long idElection) throws ParametersException {
		Voter voter = getVoterR.getVoter(nif);

		for (VotedElection votedElection : voter.getVotedElections()) {
			if (votedElection.getIdElection() == idElection)
				return true;
		}

		return false;
	}

}
