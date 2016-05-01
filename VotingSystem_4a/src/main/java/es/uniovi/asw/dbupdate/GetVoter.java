package es.uniovi.asw.dbupdate;

import es.uniovi.asw.dbupdate.repositories.VoterRepository;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.util.ParametersException;

/**
 * GetVoter Created by ivan on 2/04/16.
 */
public interface GetVoter {

	Voter getVoter(VoterRepository voterRepo,Long idVoter) throws ParametersException;

	Voter getVoterByNif(VoterRepository voterRepo,String nif) throws ParametersException;

	Voter getVoterByEmail(VoterRepository voter,String email) throws ParametersException;
}
