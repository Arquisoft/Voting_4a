package es.uniovi.asw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.List;

import es.uniovi.asw.model.*;
import es.uniovi.asw.persistence.VotacionesService;
import es.uniovi.asw.persistence.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:0")
public class RepoTest {

	@Autowired
	private VotingPlaceRepository votingPlaceRepo;

	@Autowired
	private VoteRepository votoRepo;

	@Autowired
	private ElectionRepository votacionRepo;

	@Autowired
	private VotacionesService votacionesService;

	@Autowired
	private CandidatureRepository opcionRepo;

	@Autowired
	private DistrictRepository districtRepository;

	/**
	 * Metodo que comprueba el correcto funcionamiento de la tabla voto de la
	 * base de datos.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testVotos() throws Exception {
		List<Vote> votos = (List<Vote>) votoRepo.findAll();
		assertEquals(9, votos.size());
		List<Vote> votosNoLeidos = votoRepo.findByRead(false);
		assertEquals(9, votosNoLeidos.size());
		List<Vote> votosLeidos = votoRepo.findByRead(true);
		assertEquals(0, votosLeidos.size());
		List<Vote> votosColegio1 = votoRepo.findByVotingPlace(votingPlaceRepo.findOne(1L));
		assertEquals(3, votosColegio1.size());
		List<Vote> votosColegio2 = votoRepo.findByVotingPlace(votingPlaceRepo.findOne(2L));
		assertEquals(1, votosColegio2.size());
	}

	/**
	 * Metodo que comprueba el correcto funcionamiento de la tabla votacion de
	 * la base de datos.
	 *
	 * @throws Exception
	 */
	@Test
	public void testVotacion() throws Exception {
		List<Election> votaciones = votacionesService.getVoteInfo(true);
		assertNotNull(votaciones);
		votaciones = (List<Election>) votacionRepo.findAll();
		assertEquals(1, votaciones.size());
		Election votacion = votacionRepo.findOne((long) 1);
		assertNotNull(votacion);
	}

	/**
	 * Metodo que comprueba el correcto funcionamiento de la tabla colegio
	 * electoral de la base de datos.
	 *
	 * @throws Exception
	 */
	@Test
	public void testVotingPlace() throws Exception {
		List<VotingPlace> colegios = (List<VotingPlace>) votingPlaceRepo.findAll();
		assertEquals(4, colegios.size());
		VotingPlace votingPlace = new VotingPlace();
		votingPlace.setName("Colegio Electoral 1");
		votingPlaceRepo.save(votingPlace);
		VotingPlace colegio = votingPlaceRepo.findOne(1L);
		assertNotNull(colegio);
		assertEquals("Colegio Electoral 1", colegio.getName());
	}

	/**
	 * Metodo que comprueba el correcto funcionamiento de la tabla opcion de la
	 * base de datos.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOpcion() throws Exception {
		List<Candidature> opciones = (List<Candidature>) opcionRepo.findAll();
		assertEquals(3, opciones.size());
		opciones = (List<Candidature>) opcionRepo.findByDistrict(districtRepository.findOne(1L));
		assertEquals(3, opciones.size());
	}

}