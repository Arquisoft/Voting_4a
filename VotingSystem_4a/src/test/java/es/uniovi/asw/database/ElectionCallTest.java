package es.uniovi.asw.database;

import es.uniovi.asw.Application;
import es.uniovi.asw.dbupdate.repositories.ElectionCallRepository;
import es.uniovi.asw.model.Election;
import es.uniovi.asw.model.ElectionCall;
import es.uniovi.asw.model.types.ElectionDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * ElectionCallTest
 * Created by ivan on 2/04/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class ElectionCallTest {

	private ElectionCall electionCall;

	@Autowired
	private ElectionCallRepository electionCallRepository;

	public void setElectionCallRepository(ElectionCallRepository electionCallRepository) {
		this.electionCallRepository = electionCallRepository;
	}

	@Before
	public void setUp() throws Exception {
		electionCall = new ElectionCall();

		electionCall.setName("Referendum de Prueba");
		electionCall.setDescription("Referendum de Independencia de Asturias");

		assertNull(electionCall.getId());
		ElectionCall savedElectionCall = electionCallRepository.save(electionCall);

		assertNotNull(savedElectionCall);
		assertTrue(savedElectionCall.getId() > 0);
	}

	private Election createElection() {
		Election election = new Election();

		election.setName("Congreso 2016");
		election.setDescription("Elecciones al Congreso 2016");

		assertNull(election.getId());

		ElectionDateTime electionDateTime = new ElectionDateTime();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy H:m");

		Date startDate;
		Date endDate;
		Timestamp start = null;
		Timestamp end = null;

		try {
			startDate = dateFormat.parse("03/05/2016 10:00");
			endDate = dateFormat.parse("03/05/2016 20:30");
			start = new Timestamp(startDate.getTime());
			end = new Timestamp(endDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		electionDateTime.setStartTime(start);
		electionDateTime.setEndTime(end);
		election.setElectionDateTime(electionDateTime);

		return election;
	}

	@Test
	public void addElection() throws Exception {
		Election election = createElection();

		ElectionCall fetchedElectionCall = electionCallRepository.findOne(electionCall.getId());
		assertNotNull(fetchedElectionCall);
		assert(fetchedElectionCall.getElections().size() == 0);

		fetchedElectionCall.addElection(election);
		electionCallRepository.save(fetchedElectionCall);

		fetchedElectionCall = electionCallRepository.findOne(electionCall.getId());
		assertNotNull(fetchedElectionCall);
		assert(fetchedElectionCall.getElections().size() == 1);
	}

	@Test
	public void removeElection() throws Exception {
		Election election = createElection();
		addElection();

		ElectionCall fetchedElectionCall = electionCallRepository.findOne(electionCall.getId());
		assertNotNull(fetchedElectionCall);
		assertTrue(fetchedElectionCall.getElections().size() == 1);

		fetchedElectionCall.removeElection(election);
		electionCallRepository.save(fetchedElectionCall);

		fetchedElectionCall = electionCallRepository.findOne(electionCall.getId());
		assertNotNull(fetchedElectionCall);
		assertTrue(fetchedElectionCall.getElections().size() == 0);
	}

	@Test
	public void getElections() throws Exception {
		addElection();

		ElectionCall fetchedElectionCall = electionCallRepository.findOne(electionCall.getId());
		assertNotNull(fetchedElectionCall);
		assertTrue(fetchedElectionCall.getElections().size() == 1);
	}

	@Test
	public void getId() throws Exception {
		addElection();

		ElectionCall fetchedElectionCall = electionCallRepository.findOne(electionCall.getId());
		assertNotNull(fetchedElectionCall);
		assertTrue(fetchedElectionCall.getId() > 0);
	}

	@Test
	public void getName() throws Exception {
		addElection();

		ElectionCall fetchedElectionCall = electionCallRepository.findOne(electionCall.getId());
		assertNotNull(fetchedElectionCall);
		assertNotNull(fetchedElectionCall.getName());
	}

	@Test
	public void setName() throws Exception {
		addElection();

		ElectionCall fetchedElectionCall = electionCallRepository.findOne(electionCall.getId());
		assertNotNull(fetchedElectionCall);

		fetchedElectionCall.setName("Elecciones 2016");
		electionCallRepository.save(fetchedElectionCall);

		fetchedElectionCall = electionCallRepository.findOne(electionCall.getId());
		assertNotNull(fetchedElectionCall);
		assertEquals("Elecciones 2016", fetchedElectionCall.getName());
	}

	@Test
	public void getDescription() throws Exception {
		addElection();

		ElectionCall fetchedElectionCall = electionCallRepository.findOne(electionCall.getId());
		assertNotNull(fetchedElectionCall);
		assertNotNull(fetchedElectionCall.getDescription());
	}

	@Test
	public void setDescription() throws Exception {
		addElection();

		ElectionCall fetchedElectionCall = electionCallRepository.findOne(electionCall.getId());
		assertNotNull(fetchedElectionCall);

		fetchedElectionCall.setDescription("Elecciones al Congreso y Senado 2016");
		electionCallRepository.save(fetchedElectionCall);

		fetchedElectionCall = electionCallRepository.findOne(electionCall.getId());
		assertNotNull(fetchedElectionCall);
		assertEquals("Elecciones al Congreso y Senado 2016", fetchedElectionCall.getDescription());
	}
}