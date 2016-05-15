package es.uniovi.asw.dbupdate.ports;

import es.uniovi.asw.Application;
import es.uniovi.asw.model.Vote;
import es.uniovi.asw.model.Voter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * RegisterVotePTest
 * Created by ivan on 12/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:0")
@Transactional
public class RegisterVotePTest {

	@Autowired
	private RegisterVoteP registerVoteP;

	@Test
	public void insertVote() throws Exception {
		Vote vote = registerVoteP.insertVote(1L, 1L);
		assertNotNull(vote.getId());
	}

	@Test
	public void registerVoter() throws Exception {
		Voter voter = registerVoteP.registerVoter(11L, 1L);
		assertNotNull(voter.getId());
	}

}