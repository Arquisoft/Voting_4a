package es.uniovi.asw.parametersmanager.ports;

import es.uniovi.asw.dbupdate.ports.GetParametersP;
import es.uniovi.asw.model.ElectionCall;
import es.uniovi.asw.util.ParametersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.parametersmanager.ReadParameters;

/**
 * RParameters
 * Created by ivan on 2/04/16.
 */
@Service
public class RParameters implements ReadParameters {

	@Autowired
	private GetParametersP getParametersP;

	@Override
	public Iterable<ElectionCall> getElectionCalls() throws ParametersException {
		return getParametersP.getElectionCalls();
	}

}
