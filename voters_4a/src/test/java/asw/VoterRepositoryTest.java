package asw;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import asw.DBRepository.VoterRepository;
import asw.Model.Voter;
import asw.util.MD5;

@ActiveProfiles(profiles="test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class VoterRepositoryTest {

    @Value("${local.server.port}")
    private int port;
    
    @Autowired
    private VoterRepository repository;

    private static final int NUM_VOTER = 4;

    
	/**
	 * Método para introducir NUM_VOTER 
	 * votantes en el repositorio antes de cada test.
	 * @throws Exception
	 */
	@Before
	public void setUpBefore() throws Exception {
		for (int i = 0; i < NUM_VOTER; i++) {
			repository.save(new Voter("Nombre"+i, "Email"+i, 
					"Password"+i, "Dni"+i));
		}
	}
	
	/**
	 * Método para vaciar el repositorio después de cada test.
	 * @throws Exception
	 */
	@After
	public void setUpAfter() throws Exception {
		repository.deleteAll();
	}
	
	
	/**
	 * Método que comprueba que se añaden correctamente
	 * votantes al repositorio.
	 * @throws Exception
	 */
	@Test
	public void testExist() throws Exception {
		for (int i = 0; i < NUM_VOTER; i++) {
			assertEquals("Nombre"+i, repository.findByEmailAndPassword("Email"+i,
					MD5.getMD5("Password"+i)).getNombre());
		}
	}
	
	/**
	 * Método que comprueba que se actualiza correctamente
	 * la información de un votante.
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		assertEquals(NUM_VOTER,repository.count());
		//Obtenemos un votante.
		Voter votante = repository.findByEmailAndPassword("Email1",
				MD5.getMD5("Password1"));
		assertEquals("Nombre1", votante.getNombre());
		//Modificamos un atributo del votante y lo actualizamos. 
		votante.setPassword("12");
		votante.setDNI("1234");
		votante.setColegioelectoral(200);
		votante=repository.save(votante);
		assertEquals(4,repository.count());
		//Comprobamos la actualización.
		assertEquals(votante, repository.findByEmailAndPassword("Email1",
				"12"));
		assertEquals("1234", repository.findByEmailAndPassword("Email1",
				"12").getDni());
		assertEquals(200, repository.findByEmailAndPassword("Email1",
				"12").getColegioelectoral());
	}
	
	/**
	 * Método que comprueba que un votante elimina correctamente.
	 * @throws Exception
	 */
	@Test
	public void testDelete() throws Exception {
		assertEquals(NUM_VOTER,repository.count());
		//Eliminamos un votante y comprobamos que se ha eliminado en cada iteración.
		for (int i = 0; i < NUM_VOTER; i++) {
			repository.delete(repository.findByEmailAndPassword("Email"+i,
					MD5.getMD5("Password"+i)));
			assertEquals(NUM_VOTER-i-1, repository.count());
		}
	}
}


























