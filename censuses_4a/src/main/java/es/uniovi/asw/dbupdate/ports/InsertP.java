package es.uniovi.asw.dbupdate.ports;

import es.uniovi.asw.dbupdate.Insert;
import es.uniovi.asw.dbupdate.repositories.VoterRepository;
import es.uniovi.asw.model.Voter;
import es.uniovi.asw.util.ReadCensusException;

import java.io.IOException;

public class InsertP implements Insert {

	private WreportR report;
	private static VoterRepository voterRepository;

	public static void setVoterRepository(VoterRepository vr) {
		voterRepository = vr;
	}

	public InsertP() {
		report = new WreportR();
	}

	@Override
	public void insertVoter(Voter voter) throws IOException {

		try {

			if (verify(voter))
				voterRepository.save(voter);

		}

		catch (ReadCensusException e) {
			report.log(e.getMessage());
		}
	}

	@Override
	public void logMessage(String message) throws IOException {
		report.log(message);
	}

	private boolean verify(Voter voter) throws ReadCensusException {
		
		if (voter == null) {
			throw new ReadCensusException("[ERROR] [InsertDB] Se ha recibido un votante sin datos, no se puede insertar en la BB.DD");
		}
		
		if (voter.getNif() == null || voter.getNif().equals("")) {
			throw new ReadCensusException("[ERROR] [InsertDB] Falta el NIF del usuario, no se puede insertar en la BB.DD");
		}
		
		if (voter.getName() == null || voter.getName().equals("")) {
			throw new ReadCensusException("[ERROR] [InsertDB] Falta el nombre del usuario " + voter.getNif() + ", no se puede insertar en la BB.DD");
		}
		
		if (voter.getEmail() == null || voter.getEmail().equals("")) {
			throw new ReadCensusException("[ERROR] [InsertDB] Falta el email del usuario " + voter.getNif() + ", no se puede insertar en la BB.DD");
		}
		
		if (voter.getPassword() == null || voter.getPassword().equals("")) {
			throw new ReadCensusException("[ERROR] [InsertDB] Falta el password del usuario " + voter.getNif() + ", no se puede insertar en la BB.DD");
		}
		
		if (voter.getCode() == null || voter.getCode().toString().equals("")) {
			throw new ReadCensusException("[ERROR] [InsertDB] Falta el código del colegio del usuario " + voter.getNif() + ", no se puede insertar en la BB.DD");
		}

		if (voterRepository.findByNif(voter.getNif()) != null) {
			throw new ReadCensusException("[ERROR] [InsertDB] El votante " + voter.getNif() + " ya existe");
		}

		return true;
		
	}

}
