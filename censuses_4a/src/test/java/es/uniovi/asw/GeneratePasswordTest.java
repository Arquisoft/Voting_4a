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

public class GeneratePasswordTest {
	
	private static final Logger logger = Logger.getLogger(GeneratePasswordTest.class.getName());

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
	public void assertPasswordGenerated() {
		Map<String, Object> voter = voters.get(0);
		assertNotEquals(null, voter.get("password"));
		assertEquals(8, voter.get("password").toString().length());
	}
	
}