package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
        // prevent object creation
    }

    public static WebDriver initDriver() {
        if (driver.get() == null) {
            WebDriverManager.chromedriver().setup();
            WebDriver webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
            driver.set(webDriver);
        }
        return driver.get();
    }

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
