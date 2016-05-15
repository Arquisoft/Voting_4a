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
 * GetVoterPTest
 * Created by ivan on 15/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:0")
@Transactional
public class GetVoterPTest {

	@Autowired
	private GetVoterP getVoterP;

	@Test
	public void getVoter() throws Exception {
		assertEquals("11111111A", getVoterP.getVoter(1L).getNif());
	}

	@Test
	public void getVoterByNif() throws Exception {
		assertEquals("11111111A", getVoterP.getVoter(1L).getNif());
	}

	@Test
	public void getVoterByEmail() throws Exception {
		assertEquals("perico@servidor.com", getVoterP.getVoter(1L).getEmail());
	}

}