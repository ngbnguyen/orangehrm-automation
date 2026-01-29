package stepDefinitions.ui;

import driver.DriverFactory;
import io.cucumber.java.en.Given;
import pages.LoginPage;
import utils.EnvUtils;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("Login as Admin")
    public void login_as_admin() {
        loginPage = new LoginPage(DriverFactory.getDriver());

        loginPage.open();
        loginPage.login(
                EnvUtils.getUsername(),
                EnvUtils.getPassword()
        );
        loginPage.waitForPageLoad();
    }
}
