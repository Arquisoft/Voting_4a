package es.uniovi.asw.dbupdate.ports;

import es.uniovi.asw.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * GetParametersPTest
 * Created by ivan on 12/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:0")
@Transactional
public class GetParametersPTest {

	@Autowired
	private GetParametersP getParametersP;

	@Test
	public void getElectionCalls() throws Exception {
		assertEquals(1, ((List)getParametersP.getElectionCalls()).size());
	}

	@Test
	public void getElectionCall() throws Exception {
		assertNotNull(getParametersP.getElectionCall(1L).getId());
	}

	@Test
	public void getElections() throws Exception {
		assertEquals(1, ((List)getParametersP.getElections(1L)).size());
	}

	@Test
	public void getElections1() throws Exception {
		assertEquals(1, ((List)getParametersP.getElections()).size());
	}

	@Test
	public void getRegions() throws Exception {
		assertEquals(1, ((List)getParametersP.getRegions(1L)).size());
	}

	@Test
	public void getDistricts() throws Exception {
		assertEquals(1, ((List)getParametersP.getDistricts(1L)).size());
	}

	@Test
	public void getCandidatures() throws Exception {
		assertEquals(2, ((List)getParametersP.getCandidatures(1L)).size());
	}

	@Test
	public void getVotingPlaces() throws Exception {
		assertEquals(4, ((List)getParametersP.getVotingPlaces()).size());
	}

}