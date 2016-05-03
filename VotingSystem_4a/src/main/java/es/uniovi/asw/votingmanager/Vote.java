package es.uniovi.asw.votingmanager;

import es.uniovi.asw.util.ParametersException;

/**
 * Vote Created by ivan on 2/04/16.
 */
public interface Vote {

	void registerVote(Long idVoter, Long idVotingPlace, Long idCandidature) throws ParametersException;

}
