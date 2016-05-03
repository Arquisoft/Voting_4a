package asw;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import asw.DBRepository.VoterRepository;
import asw.Model.Voter;

@ActiveProfiles(profiles="test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class VoterControllerTest {

    @Value("${local.server.port}")
    private int port;
    
    private MockMvc mockMvc;
    
    @Autowired
    private VoterRepository repository;

    
    @Autowired
    private WebApplicationContext webApplicationContext;

	@Before
	public void setUpBefore() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}
	
	@After
	public void setUpAfter() throws Exception {
		repository.deleteAll();
	}
	
	
	/**
	 * Método que comprueba que el email y contraseña enviados para la consulta 
	 * pertenecen a un votante que existe y está en nuestro repositorio.
	 * @throws Exception
	 */
	@Test
	public void testVoterExists() throws Exception {
		
	//--Se crea el votante y se añade al repositorio.
		Voter votante = new Voter("Jose", "j","jose", "1234");
		repository.save(votante); 
		
	//--Se comprueba que se añade al repositorio
		assertEquals(1, repository.count());
		
	//--Comprobamos que el votante existe y tiene el formato adecuado.
		mockMvc
		.perform(post("/user")
		.contentType(MediaType.APPLICATION_JSON)
		.content("{\"email\":\"j\", \"password\": \"jose\"}")
		).andExpect(status().is(202));
		
		
	//--Más pruebas con diferentes votantes
		repository.save(new Voter("Bernardo", "bernardin@uniovi.es","3s70", "76355422B")); 
		assertEquals(2, repository.count());
		
		mockMvc
		.perform(post("/user")
		.contentType(MediaType.APPLICATION_JSON)
		.content("{\"email\":\"bernardin@uniovi.es\", \"password\": \"3s70\"}")
		).andExpect(status().is(202));
	//-------------
		repository.save(new Voter("Luis", "ludu@outlook.es","ruffles", "76687742C")); 
		assertEquals(3, repository.count());
		
		mockMvc
		.perform(post("/user")
		.contentType(MediaType.APPLICATION_JSON)
		.content("{\"email\":\"ludu@outlook.es\", \"password\": \"ruffles\"}")
		).andExpect(status().is(202));
	}
	
	/**
	 * Método que comprueba que se devuelve un error al 
	 * intentar enviar una dirección de un votante que no existe.
	 * @throws Exception
	 */
	@Test
	public void testVoterNoExists() throws Exception {
		mockMvc
		.perform(post("/user")
		.contentType(MediaType.APPLICATION_JSON)
		.content("{\"email\":\"noexiste\", \"password\": \"noexiste\"}")
		).andExpect(status().is(400));
	}
	
	/**
	 * Método que comprueba que se devuelve un error al 
	 * intentar enviar una contraseña incorrecta asociada 
	 * al correo de un votante.
	 * @throws Exception
	 */
	@Test
	public void testWrongPassword() throws Exception {
		repository.save(new Voter("Jose", "emailJose","j", "1234")); 
		assertEquals(1, repository.count());
		
		mockMvc
		.perform(post("/user")
		.contentType(MediaType.APPLICATION_JSON)
		.content("{\"email\":\"emailJose\", \"password\": \"jj\"}")
		).andExpect(status().is(400));
	}
	
	/**
	 * Método que comprueba que se realiza el cambio 
	 * de contraseña de un votante correctamente.
	 * @throws Exception
	 */
	@Test
	public void testChangePasswordOK() throws Exception {
		repository.save(new Voter("Jose", "j","jose", "jose")); 
		assertEquals(1, repository.count());
		
		//Realizamos el cambio de contraseña
		mockMvc
		.perform(post("/password")
		.contentType(MediaType.APPLICATION_JSON)
		.content("{\"email\":\"j\", \"oldPassword\": \"jose\", \"newPassword\": \"r\"}")
		).andExpect(status().is(202));
		
		//Comprobamos que se ha realizado el cambio de contraseña
		
		mockMvc
		.perform(post("/user")
		.contentType(MediaType.APPLICATION_JSON)
		.content("{\"email\":\"j\", \"password\": \"r\"}")
		).andExpect(status().is(202));
		
	}
	
	/**
	 * Método que comprueba que se devuelve un error al 
	 * intentar cambiar la contraseña asociada a un votante 
	 * la contraseña actual introducida es incorrecta.
	 * @throws Exception
	 */
	@Test
	public void testChangePasswordFalse() throws Exception {
		
		repository.save(new Voter("Jose", "j","j", "1234")); 
		assertEquals(1, repository.count());
		
		//Le pasamos una contraseña actual incorrecta.
		mockMvc
		.perform(post("/password")
		.contentType(MediaType.APPLICATION_JSON)
		.content("{\"email\":\"j\", \"oldPassword\": \"p\", \"newPassword\": \"z\"}")
		).andExpect(status().is(400));
		
		//Funciona correctamente con la contraseña actual correcta
		mockMvc
		.perform(post("/password")
		.contentType(MediaType.APPLICATION_JSON)
		.content("{\"email\":\"j\", \"oldPassword\": \"j\", \"newPassword\": \"z\"}")
		).andExpect(status().is(202));
	}
	
	//Test para los formularios HTML
	
	@Test
	public void testPasswordHTMLFormFallo() throws Exception {
		repository.save(new Voter("Jose", "emailJose","j", "1234")); 
		assertEquals(1, repository.count());
		
		RequestBuilder request = post("/cambiarPass")
		        .param("email", "emailJose")
		        .param("oldPassword", "ja")
				.param("newPassword", "nueva");

		    mockMvc
		        .perform(request)
		        .andExpect(model().attribute("voterdto", hasProperty("email", is("emailJose"))))
		        .andExpect(view().name("errorPassChanged"));
	}
	
	@Test
	public void testPasswordHTMLFormAcierto() throws Exception {
		repository.save(new Voter("Jose", "emailJose","j", "1234")); 
		assertEquals(1, repository.count());
		
		RequestBuilder request = post("/cambiarPass")
		        .param("email", "emailJose")
		        .param("oldPassword", "j")
				.param("newPassword", "nueva");

		    mockMvc
		        .perform(request)
		        .andExpect(model().attribute("voterdto", hasProperty("email", is("emailJose"))))
		        .andExpect(model().attribute("voterdto", hasProperty("oldPassword", is("j"))))
		        .andExpect(model().attribute("voterdto", hasProperty("newPassword", is("nueva"))))
		        .andExpect(view().name("passChanged"));
	}
	
	@Test
	public void testVotoHTMLFormAcierto() throws Exception {
		Voter votante = new Voter("Jose", "emailJose","j", "1234");
		votante.setColegioelectoral(450);
		repository.save(votante); 
		assertEquals(1, repository.count());
		
		RequestBuilder request = post("/")
		        .param("email", "emailJose")
		        .param("password", "j");

		    mockMvc
		        .perform(request)
		        .andExpect(model().attribute("resultado", hasProperty("email", is("emailJose"))))
		        .andExpect(model().attribute("resultado", hasProperty("colegioelectoral", is(450L))))
		        .andExpect(view().name("votarSuccess"));
		    
	}
	
	@Test
	public void testVotoHTMLFormFallo() throws Exception {
		repository.save(new Voter("Jose", "emailJose","j", "1234")); 
		assertEquals(1, repository.count());
		
		RequestBuilder request = post("/")
		        .param("email", "emailJose")
		        .param("password", "passErronea");

		    mockMvc
		        .perform(request)
		        .andExpect(model().attribute("resultado", hasProperty("email", is("emailJose"))))
		        .andExpect(view().name("votarError"));
		    
	}
	
	@Test
	public void testGETMethods() throws Exception {
		
		RequestBuilder request = get("/cambiarPass");
	    mockMvc
	        .perform(request)
	        .andExpect(view().name("nuevaPass"));
	    
	    request = get("/");
	    mockMvc
	        .perform(request)
	        .andExpect(view().name("votar"));
		    
	}

	
}
