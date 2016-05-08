package es.uniovi.asw;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.springframework.boot.test.IntegrationTest;

@RunWith(Cucumber.class)
// @RunWith(SpringJUnit4ClassRunner.class)
// @SpringApplicationConfiguration(classes = Application.class)
// @ContextConfiguration(classes = Application.class, loader =
// SpringApplicationContextLoader.class)
// @WebAppConfiguration
// @IntegrationTest({ "server.port=0" })
@CucumberOptions(features = "src/test/resources/features")
@IntegrationTest("server.port:0")
public class CucumberTest {
}