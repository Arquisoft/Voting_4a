package es.uniovi.asw.votingmanager.ports;

import es.uniovi.asw.dbupdate.ports.GetVoterP;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.util.ParametersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * GetVoterR Created by ivan on 2/04/16.
 */
@Service
public class GetVoterR {

	@Autowired
	private GetVoterP getVoterP;

	public Voter getVoter(Long id) throws ParametersException {
		return getVoterP.getVoter(id);
	}

	public Voter getVoter(String email) throws ParametersException {
		return getVoterP.getVoterByEmail(email);
	}
}
