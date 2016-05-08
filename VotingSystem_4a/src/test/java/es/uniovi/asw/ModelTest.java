package es.uniovi.asw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.District;
import es.uniovi.asw.model.Election;
import es.uniovi.asw.model.ElectionCall;
import es.uniovi.asw.model.Region;
import es.uniovi.asw.model.Vote;
import es.uniovi.asw.model.VotingPlace;
import org.springframework.boot.test.IntegrationTest;

@IntegrationTest("server.port:0")
public class ModelTest {

	ElectionCall ec;
	Election e;

	@Before
	public void setUp() {

		ec = new ElectionCall("convocatoria", "asdf");
		e = new Election("2016", "eleccion prueba");

	}

	@Test
	public void testElecciones() {

		ec.addElection(e);

		assertEquals(1, ec.getElections().size());

	}

	@Test
	public void testRegiones() {
		Region r = new Region("España");
		e.addRegion(r);
		r.addDistrict(new District("Unica"));

		assertEquals(1, e.getRegions().size());
		assertEquals(1, r.getDistricts().size()); // Circunscripcion unica
		assertNotNull(e);

	}

	@Test
	public void testVotingPlace() {
		VotingPlace vp = new VotingPlace("Colegio 1");
		vp.addVote(new Vote());
		vp.addVote(new Vote());
		vp.addVote(new Vote());

		assertEquals(3, vp.getVotes().size());
	}

}
