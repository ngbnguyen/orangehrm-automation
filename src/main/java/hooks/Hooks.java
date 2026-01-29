package hooks;

import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.json.JSONObject;

public class Hooks {

    @Before
    public void setUp() {
        System.out.println(">>> HOOKS BEFORE RUNNING <<<");
        DriverFactory.initDriver();
    }

    @After
    public void tearDown() {
        if (DriverFactory.getDriver() != null) {
            DriverFactory.quitDriver();
        }
    }
}
