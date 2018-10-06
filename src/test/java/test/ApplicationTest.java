package test;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApplicationTest {

    private static final String JMETER_HOME = "/home/olkyupwork/Softwares/ApacheJMeter";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void verifyServerIsUpAndRespond() {
        Assertions.assertThat(restTemplate.getForEntity("http://localhost:" + port + "/ping", String.class).getStatusCode())
            .isIn(HttpStatus.OK, HttpStatus.BAD_REQUEST, HttpStatus.NOT_FOUND);
    }

    @Test
    public void jmeterTest() throws Exception {
        // JMeter Engine
        StandardJMeterEngine jmeter = new StandardJMeterEngine();

        // Initialize Properties, logging, locale, etc.
        JMeterUtils.loadJMeterProperties(JMETER_HOME + "/bin/jmeter.properties");
        JMeterUtils.setJMeterHome(JMETER_HOME);
        JMeterUtils.initLocale();

        // Initialize JMeter SaveService
        SaveService.loadProperties();

        // Load existing test plan from .jmx
        final URL url = ApplicationTest.class.getClassLoader().getResource("test-rest-application-test-plan.jmx");
        final HashTree testPlanTree = SaveService.loadTree(new File(url.toURI()));

        // Run JMeter Test
        jmeter.configure(testPlanTree);
        jmeter.run();
    }
}
