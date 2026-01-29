package stepDefinitions.ui;

import driver.DriverManager;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;

public class BasePageSteps {
    WebDriver driver = DriverManager.getDriver();
    DashboardPage dashboardPage = new DashboardPage(driver);

    @Given("User navigates to page: {string}")
    public void openPage(String page){
        dashboardPage.clickOnMenuItem(page);
    }


}
