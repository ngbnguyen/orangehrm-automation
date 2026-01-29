package hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import utils.ConfigUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

public class AllureHooks {

    @After("@ui")
    public void attachScreenshot(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        if (scenario.isFailed() && driver != null) {
            byte[] screenshot =
                    ((TakesScreenshot) DriverManager.getDriver())
                            .getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(
                    "Screenshot - " + scenario.getName(),
                    new ByteArrayInputStream(screenshot)
            );
        }
    }

    @BeforeAll
    public static void attachEnvProps() {
        try {
            File dir = new File("target/allure-results");
            dir.mkdirs();

            Properties props = new Properties();
            props.setProperty("Browser", System.getProperty("browser", "Chrome"));
            props.setProperty("Base.URL", ConfigUtils.get("ui.baseUrl"));
            props.setProperty("OS", System.getProperty("os.name"));
            props.setProperty("Java", System.getProperty("java.version"));

            try (FileOutputStream fos =
                         new FileOutputStream("target/allure-results/environment.properties")) {
                props.store(fos, "Allure Environment");
            }

            System.out.println("-------------------- Allure environment written --------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
