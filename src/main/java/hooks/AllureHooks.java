package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import driver.DriverManager;

public class AllureHooks {

    @After
    public void attachScreenshot(Scenario scenario) {

        if (scenario.isFailed() && DriverManager.getDriver() != null) {
            saveScreenshot(DriverManager.getDriver());
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshot(org.openqa.selenium.WebDriver driver) {
        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
    }
}
