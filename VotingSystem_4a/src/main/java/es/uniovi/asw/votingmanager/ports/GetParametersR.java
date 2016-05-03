package es.uniovi.asw.votingmanager.ports;

import es.uniovi.asw.dbupdate.ports.GetParametersP;
import es.uniovi.asw.model.*;
import es.uniovi.asw.util.ParametersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * GetParametersR Created by ivan on 2/04/16.
 */
@Service
public class GetParametersR {

	@Autowired
	private GetParametersP getParametersP;

	public Election getReferendum(Long idElectionCall) throws ParametersException {
		for (Election election : getParametersP.getElections(idElectionCall))
			return election;

		return null;
	}

	public Iterable<ReferendumOption> getReferendumOptions(Long idElection) throws ParametersException {
			for (Region region : getParametersP.getRegions(idElection))
				for (District district : getParametersP.getDistricts(region.getId()))
					return getParametersP.getCandidatures(district.getId());

		return null;
	}
}
