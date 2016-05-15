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
 * Created by ivan on 15/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:0")
@Transactional
public class GetParametersRTest {

	@Autowired
	private GetParametersR getParametersR;

	@Test
	public void getReferendum() throws Exception {
		assertNotNull(getParametersR.getReferendum(1L));
	}

	@Test
	public void getReferendumOptions() throws Exception {
		assertNotNull(getParametersR.getReferendumOptions(1L));
	}

}