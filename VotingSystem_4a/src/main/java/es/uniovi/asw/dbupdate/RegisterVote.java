package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.Vote;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.util.ParametersException;

/**
 * RegisterVote Created by ivan on 2/04/16.
 */
public interface RegisterVote {

	Vote insertVote(Long idCandidature, Long idVotingPlace) throws ParametersException;

	Voter registerVoter(Long id, Long idElection) throws ParametersException;

}
