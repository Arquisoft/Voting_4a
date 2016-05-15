package es.uniovi.asw.votingmanager.ports;

import es.uniovi.asw.Application;
import es.uniovi.asw.dbupdate.repositories.VoteRepository;
import es.uniovi.asw.dbupdate.repositories.VoterRepository;
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
 * RVoteTest
 * Created by ivan on 15/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:0")
@Transactional
public class RVoteTest {

	@Autowired
	private RVote rVote;

	@Autowired
	private VoterRepository voterRepository;

	@Autowired
	private VoteRepository voteRepository;

	@Test
	public void registerVote() throws Exception {
		assertEquals(9, ((List) voteRepository.findAll()).size());
		rVote.registerVote(11L, 1L, 1L, 1L);
		assertEquals(10, ((List) voteRepository.findAll()).size());
	}

	@Test
	public void markVoterVoted() throws Exception {
		assertEquals(0, voterRepository.findOne(11L).getVotedElections().size());
		rVote.markVoterVoted(11L, 1L);
		assertEquals(1, voterRepository.findOne(11L).getVotedElections().size());
	}

}