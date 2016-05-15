package es.uniovi.asw.dbupdate.ports;

import es.uniovi.asw.Application;
import es.uniovi.asw.model.*;
import es.uniovi.asw.model.types.ElectionDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * InsertPTest
 * Created by ivan on 15/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:0")
@Transactional
public class InsertPTest {

	@Autowired
	private InsertP insertP;

	private ElectionCall electionCall;
	private Election election;
	private Region region;
	private District district;
	private ReferendumOption referendumOption;

	@Test
	public void insertElectionCall() throws Exception {
		electionCall = new ElectionCall();
		electionCall.setName("Elecciones de Prueba");
		electionCall.setDescription("Elecciones de Prueba");
		electionCall = insertP.insertElectionCall(electionCall);
		assertNotNull(electionCall.getId());
	}

	@Test
	public void insertElection() throws Exception {
		insertElectionCall();
		ElectionDateTime electionDateTime = new ElectionDateTime(new Timestamp(Calendar.getInstance().getTime().getTime()), new Timestamp(Calendar.getInstance().getTime().getTime()));
		election = new Election();
		election.setName("Elección Senado");
		election.setDescription("Elección Senado");
		election.setElectionDateTime(electionDateTime);
		election = insertP.insertElection(electionCall.getId(), election);
		assertNotNull(election.getId());
	}

	@Test
	public void insertRegion() throws Exception {
		insertElection();
		region = new Region();
		region.setName("España");
		region = insertP.insertRegion(election.getId(), region);
		assertNotNull(region.getId());
	}

	@Test
	public void insertDistrict() throws Exception {
		insertRegion();
		district = new District();
		district.setName("Estado");
		district = insertP.insertDistrict(region.getId(), district);
		assertNotNull(district.getId());
	}

	@Test
	public void insertReferendumOption() throws Exception {
		insertDistrict();
		referendumOption = new ReferendumOption();
		referendumOption.setOption("Sí");
		referendumOption = insertP.insertReferendumOption(district.getId(), referendumOption);
		assertNotNull(referendumOption.getId());
	}

	@Test
	public void insertVotingPlace() throws Exception {
		VotingPlace votingPlace = new VotingPlace();
		votingPlace.setName("Colegio Electoral 5");
		votingPlace = insertP.insertVotingPlace(votingPlace);
		assertNotNull(votingPlace.getId());

	}

	@Test
	public void insertVoter() throws Exception {
		Voter voter = new Voter();
		voter.setName("Juan");
		voter.setNif("11122233A");
		voter.setEmail("email@email.es");
		voter.setIdVotingPlace(1L);
		voter.setPassword("AAAAAAAA");
		voter = insertP.insertVoter(1L, voter);
		assertNotNull(voter.getId());
	}

}