package stepDefinitions.ui;

import driver.DriverFactory;
import io.cucumber.java.en.Given;
import pages.DashboardPage;

public class DashboardSteps {
    private DashboardPage dashboardPage;

    @Given("Click on menu: Recruitment")
    public void click_on_recruitment_item() {
        dashboardPage = new DashboardPage(DriverFactory.getDriver());
        dashboardPage.clickOnMenuItem("recruitment");
        dashboardPage.waitForPageLoad();
    }
}
