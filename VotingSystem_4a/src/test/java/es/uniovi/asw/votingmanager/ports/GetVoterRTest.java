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
 * GetVoterRTest
 * Created by ivan on 12/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:0")
@Transactional
public class GetVoterRTest {

	@Autowired
	private GetVoterR getVoterR;

	@Test
	public void getVoter() throws Exception {
		assertNotNull(getVoterR.getVoter(1L));
	}

	@Test
	public void getVoter1() throws Exception {
		assertNotNull(getVoterR.getVoter("11111111A"));
	}

}