package es.uniovi.asw.parametersmanager.ports;

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
 * InsertRTest
 * Created by ivan on 12/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:0")
@Transactional
public class InsertRTest {

	@Autowired
	private InsertR insertR;

	@Autowired
	private RParameters rParameters;

	@Test
	public void createReferendum() throws Exception {
		assertEquals(1, ((List)rParameters.getElections()).size());
		insertR.createReferendum("Referendum", "Descripción", "2016/01/01 10:00", "2016/01/01 20:00", "Pregunta", "Sí", "No");
		assertEquals(2, ((List)rParameters.getElections()).size());
	}

}