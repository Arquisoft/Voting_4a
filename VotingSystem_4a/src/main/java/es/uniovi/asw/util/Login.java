package es.uniovi.asw.util;

import es.uniovi.asw.dbupdate.ports.GetVoterP;
import es.uniovi.asw.model.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Login {

	@Autowired
	private GetVoterP getVoterP;

	public Object auth(String email, String password) throws ParametersException {

		if (email.equals("junta@electoral.es") && password.equals("admin")) {
			return "admin";
		}

		else {

			Voter voter = getVoterP.getVoterByEmail(email);

			if (voter.getPassword().equals(password)) {
				return voter;
			} else {
				return null;
			}
		}

	}

}
