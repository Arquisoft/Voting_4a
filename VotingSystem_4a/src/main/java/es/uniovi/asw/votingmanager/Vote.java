package es.uniovi.asw.votingmanager;

import es.uniovi.asw.util.ParametersException;

/**
 * Vote Created by ivan on 2/04/16.
 */
public interface Vote {

	void registerVote(Long idVoter, Long idVotingPlace, Long idElection, Long idCandidature) throws ParametersException;

	void markVoterVoted(Long idVoter, Long idElection) throws ParametersException;
}
