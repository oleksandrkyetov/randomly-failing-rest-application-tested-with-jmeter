package test;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.net.URL;

@RunWith(JUnit4.class)
public class JMeterLoadTest {

    private static final String JMETER_HOME = "/home/olkyupwork/Softwares/ApacheJMeter";

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
        final URL url = JMeterLoadTest.class.getClassLoader().getResource("test-rest-application-test-plan.jmx");
        final HashTree testPlanTree = SaveService.loadTree(new File(url.toURI()));

        // Run JMeter Test
        jmeter.configure(testPlanTree);
        jmeter.run();
    }
}
