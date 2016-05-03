package es.uniovi.asw.votingmanager.ports;

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
	public boolean hasVoted(Long id) throws ParametersException {
		Voter voter = getVoterR.getVoter(id);

		return voter.hasVoted();
	}

	@Override
	public boolean hasVoted(String email) throws ParametersException {
		Voter voter = getVoterR.getVoter(email);

		return voter.hasVoted();
	}

}
