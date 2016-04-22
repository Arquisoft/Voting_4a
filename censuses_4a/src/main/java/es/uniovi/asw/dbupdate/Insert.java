package es.uniovi.asw.dbupdate;

import es.uniovi.asw.model.Voter;

import java.io.IOException;

public interface Insert {

	void insertVoter(Voter voter) throws IOException;

	void logMessage(String message) throws IOException;
	
}
