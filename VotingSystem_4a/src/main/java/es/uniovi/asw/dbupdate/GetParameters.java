package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.*;
import es.uniovi.asw.util.ParametersException;

/**
 * GetParameters Created by ivan on 2/04/16.
 */
public interface GetParameters {

	Iterable<ElectionCall> getElectionCalls() throws ParametersException;

	ElectionCall getElectionCall(Long idElectionCall) throws ParametersException;

	Iterable<Election> getElections(Long idElectionCall) throws ParametersException;

	Iterable<Election> getElections();

	Iterable<Region> getRegions(Long idElection) throws ParametersException;

	Iterable<District> getDistricts(Long idRegion) throws ParametersException;

	Iterable<ReferendumOption> getCandidatures(Long idDistrict) throws ParametersException;

	Iterable<VotingPlace> getVotingPlaces();

}
