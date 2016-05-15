package es.uniovi.asw.votingmanager.ports;

import es.uniovi.asw.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * RCheckVoterTest
 * Created by ivan on 12/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:0")
@Transactional
public class RCheckVoterTest {

	@Autowired
	private RCheckVoter rCheckVoter;

	@Test
	public void hasVoted() throws Exception {
		assertFalse(rCheckVoter.hasVoted(11L, 1L));
		assertTrue(rCheckVoter.hasVoted(1L, 1L));
	}

	@Test
	public void hasVoted1() throws Exception {
		assertFalse(rCheckVoter.hasVoted("11111124O", 1L));
		assertTrue(rCheckVoter.hasVoted("11111111A", 1L));
	}

}