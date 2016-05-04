package es.uniovi.asw.parametersmanager.ports;

import es.uniovi.asw.dbupdate.ports.GetParametersP;
import es.uniovi.asw.model.Election;
import es.uniovi.asw.model.ElectionCall;
import es.uniovi.asw.util.ParametersException;
import es.uniovi.asw.votingmanager.ports.RCheckVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.parametersmanager.ReadParameters;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * RParameters
 * Created by ivan on 2/04/16.
 */
@Service
public class RParameters implements ReadParameters {

	@Autowired
	private GetParametersP getParametersP;

	@Autowired
	private RCheckVoter rCheckVoter;

	@Override
	public Iterable<ElectionCall> getElectionCalls(Long idVoter) throws ParametersException {

		ArrayList<ElectionCall> electionCalls = new ArrayList<>();

		for (ElectionCall electionCall : getParametersP.getElectionCalls()) {
			for (Election election : electionCall.getElections()) {

				Timestamp start = election.getElectionDateTime().getStartTime();
				Timestamp end = election.getElectionDateTime().getEndTime();

				if (System.currentTimeMillis() >= start.getTime() && System.currentTimeMillis() < end.getTime())
					if (!rCheckVoter.hasVoted(idVoter, election.getId()))
						if (!electionCalls.contains(electionCall))
							electionCalls.add(electionCall);
			}
		}

		return electionCalls;
	}

	@Override
	public Iterable<Election> getElections() {
		return getParametersP.getElections();
	}

}
