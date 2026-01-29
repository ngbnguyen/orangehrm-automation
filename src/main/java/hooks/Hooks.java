package hooks;

import context.TestContext;
import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before("@ui")
    public void setUp() {
        System.out.println(">>> HOOKS BEFORE RUNNING <<<");
        DriverManager.initDriver();
    }

    @After("@ui")
    public void tearDown() {
        if (DriverManager.getDriver() != null) {
            DriverManager.quitDriver();
        }
    }
}
