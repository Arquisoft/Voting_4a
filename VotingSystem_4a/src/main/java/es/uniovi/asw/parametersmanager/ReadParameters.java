package es.uniovi.asw.parametersmanager;

import es.uniovi.asw.model.ElectionCall;
import es.uniovi.asw.util.ParametersException;

/**
 * ReadParameters
 * Created by ivan on 2/04/16.
 */
public interface ReadParameters {

	Iterable<ElectionCall> getElectionCalls() throws ParametersException;

}
