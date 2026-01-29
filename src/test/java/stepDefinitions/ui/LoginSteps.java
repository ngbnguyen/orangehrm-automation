package stepDefinitions.ui;

import driver.DriverManager;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.LoginPage;
import utils.EnvUtils;

public class LoginSteps {

    WebDriver driver = DriverManager.getDriver();
    LoginPage loginPage = new LoginPage(driver);

    @Given("Login as Admin")
    public void login_as_admin() {
        loginPage.open();
        loginPage.login(
                EnvUtils.getUsername(),
                EnvUtils.getPassword()
        );
        loginPage.waitForPageLoad();
    }
}
