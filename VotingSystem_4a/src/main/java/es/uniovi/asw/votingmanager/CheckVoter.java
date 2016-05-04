package es.uniovi.asw.votingmanager;

import es.uniovi.asw.util.ParametersException;

/**
 * CheckVoter Created by ivan on 2/04/16.
 */
public interface CheckVoter {

	boolean hasVoted(Long id, Long idElection) throws ParametersException;

	boolean hasVoted(String nif, Long idElection) throws ParametersException;

}
