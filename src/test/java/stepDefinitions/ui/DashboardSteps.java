package stepDefinitions.ui;

import driver.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.PIMPage;

public class DashboardSteps {
    WebDriver driver = DriverManager.getDriver();
    DashboardPage dashboardPage = new DashboardPage(driver);

    @Given("Click on menu: Recruitment")
    public void click_on_recruitment_item() {
        dashboardPage.clickOnMenuItem("recruitment");
        dashboardPage.waitForPageLoad();
    }

    @When("Wait for Loading spinner")
    public void waitForLoadingSpinner(){
        dashboardPage.waitForLoadingSpinnerVisible();
        dashboardPage.waitForLoadingSpinnerDisappear();
    }
}
