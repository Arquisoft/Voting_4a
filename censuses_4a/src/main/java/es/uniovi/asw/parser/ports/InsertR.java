package es.uniovi.asw.parser.ports;

import java.io.IOException;
import java.util.Map;

import es.uniovi.asw.model.Voter;
import es.uniovi.asw.util.DataValidator;
import es.uniovi.asw.util.MD5;
import es.uniovi.asw.util.ReadCensusException;
import es.uniovi.asw.util.ServicesFactory;

public class InsertR {

	public boolean insertVoter(Map<String, Object> voterData) throws IOException {

		try {
			Voter voter = verify(voterData);
			ServicesFactory.getDBUpdateService().insertVoter(voter);
		}

		catch (ReadCensusException e) {
			logMessage(e.getMessage());
			return false;
		}

		return true;
	}

	public void logMessage(String message) throws IOException {
		ServicesFactory.getDBUpdateService().logMessage(message);
	}

	private Voter verify(Map<String, Object> voter) throws ReadCensusException {
		
		if (voter.get("nif") == null || !DataValidator.isNifValid(voter.get("nif").toString())) {
			throw new ReadCensusException("[ERROR] [" + voter.get("file") + ":" + voter.get("line") + "] El NIF del usuario no tiene el formato correcto");
		}
		
		if (voter.get("name") == null || voter.get("name").toString().equals("")) {
			throw new ReadCensusException("[ERROR] [" + voter.get("file") + ":" + voter.get("line") + "] El usuario " + voter.get("nif") + " no tiene nombre");
		}
		
		if (voter.get("email") == null || !DataValidator.isEmailValid(voter.get("email").toString())) {
			throw new ReadCensusException("[ERROR] [" + voter.get("file") + ":" + voter.get("line") + "] El email del usuario " + voter.get("nif") + " no es válido");
		}
		
		if (voter.get("code") == null || !DataValidator.isNumberValid(voter.get("code").toString())) {
			throw new ReadCensusException("[ERROR] [" + voter.get("file") + ":" + voter.get("line") + "] El colegio electoral del usuario " + voter.get("nif") + " no es válido");
		}

		return new Voter(voter.get("name").toString(), voter.get("email").toString(), voter.get("nif").toString(), Integer.parseInt(voter.get("code").toString()), MD5.getMD5(voter.get("password").toString()));
		
	}

}
