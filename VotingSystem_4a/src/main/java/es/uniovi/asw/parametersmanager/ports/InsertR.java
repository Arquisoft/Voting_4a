package es.uniovi.asw.parametersmanager.ports;

import es.uniovi.asw.dbupdate.ports.InsertP;
import es.uniovi.asw.model.*;
import es.uniovi.asw.model.types.ElectionDateTime;
import es.uniovi.asw.util.ParametersException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * InsertR
 * Created by ivan on 2/04/16.
 */
@Service
public class InsertR {

	@Autowired
	private InsertP insertP;

	public void createReferendum(String name, String description, String startTime, String endTime, String electionQuestion, String electionResp1, String electionResp2) throws ParametersException, ParseException {

		if (name == null || description == null || startTime == null || endTime == null || electionQuestion == null || electionResp1 == null || electionResp2 == null) {
			throw new ParametersException("Los datos del referéndum no pueden ser nulos");
		}

		ElectionCall electionCall = new ElectionCall(name, description);
		electionCall = insertP.insertElectionCall(electionCall);

		Election election = new Election(description, electionQuestion);
		ElectionDateTime electionDateTime = getElectionDateTime(startTime, endTime);
		election.setElectionDateTime(electionDateTime);
		electionCall.addElection(election);
		election = insertP.insertElection(electionCall.getId(), election);

		Region region = new Region();
		region.setName("Referendum");
		election.addRegion(region);
		region = insertP.insertRegion(election.getId(), region);

		District district = new District();
		district.setName("Estado Español");
		region.addDistrict(district);
		district = insertP.insertDistrict(region.getId(), district);

		ReferendumOption referendumOption1 = new ReferendumOption();
		referendumOption1.setOption(electionResp1);
		district.addCandidature(referendumOption1);
		insertP.insertReferendumOption(district.getId(), referendumOption1);

		ReferendumOption referendumOption2 = new ReferendumOption();
		referendumOption2.setOption(electionResp2);
		district.addCandidature(referendumOption2);
		insertP.insertReferendumOption(district.getId(), referendumOption2);
	}

	private ElectionDateTime getElectionDateTime(String startTime, String endTime) throws ParseException {
		ElectionDateTime electionDateTime = new ElectionDateTime();

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy H:m");
		Date startDate = dateFormat.parse(startTime);
		Date endDate = dateFormat.parse(endTime);

		Timestamp start = new Timestamp(startDate.getTime());
		Timestamp end = new Timestamp(endDate.getTime());

		electionDateTime.setStartTime(start);
		electionDateTime.setEndTime(end);

		return electionDateTime;
	}
}
