package es.uniovi.asw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.parser.parsers.CensusParser;
import es.uniovi.asw.parser.parsers.XLSXParser;
import es.uniovi.asw.util.ReadCensusException;

public class LoadDataTest {
	
	private static final Logger logger = Logger.getLogger(LoadDataTest.class.getName());
	
	private CensusParser parser;
	private List<Map<String, Object>> voters;
	
	@Before
	public void setUp() {
		
		try {
			parser = new XLSXParser("src/test/resources/censo.xlsx");
			voters = parser.parse();
		} 
		
		catch (ReadCensusException e) {
			logger.info(e.getMessage());
		}
	}

	@Test
	public void assertVoter1Data() {
		Map<String, Object> voter = voters.get(0);
		assertEquals("Perico Delgado Gutiérrez", voter.get("name"));
		assertNotEquals("perico2@servidor.com", voter.get("email"));
		assertEquals("perico@servidor.com", voter.get("email"));
	}
	
	@Test
	public void assertVoter2Data() {
		Map<String, Object> voter = voters.get(1);
		assertEquals("Juan Álvarez González", voter.get("name"));
		assertNotEquals("juan@servidor2.com", voter.get("email"));
		assertEquals("juan@servidor.com", voter.get("email"));
	}
	
	@Test
	public void assertVoter3Data() {
		Map<String, Object> voter = voters.get(2);
		assertEquals("Manuel Fernández Álvarez", voter.get("name"));
		assertNotEquals("manuel@servidor.net", voter.get("email"));
		assertEquals("manuel@servidor.com", voter.get("email"));
	}

}
