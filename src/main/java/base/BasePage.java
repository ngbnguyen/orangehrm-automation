package base;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger log = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        if (driver == null) {
            throw new RuntimeException("========== WebDriver is null ==========");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50)); // Explicit wait for page
    }

    protected void openUrl(String url) {
        driver.get(url);
        waitForPageLoad();
        log.info("========== Waiting for loading page ==========");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /* ------------------ BASIC ACTIONS ------------------ */

    @Step("Click element")
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    @Step("Type text: {text}")
    protected void type(By locator, String text) {
        WebElement element =
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    protected String getInputText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
                .getAttribute("value");
    }

    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
                .getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
                    .isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void upload(By locator, String path) {

        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + path);
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement fileInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display='block';", fileInput);
        fileInput.sendKeys(file.getAbsolutePath());
    }

    public static void scrollToElement(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );
    }

    /* ------------------ WAITS ------------------ */

    @Step("Wait for toast message")
    public String waitForToast() {
        By toast = By.cssSelector(".oxd-toast-content-text");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(toast)).getText();
    }

    protected void waitForVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForDisappear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForPageLoad() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait.until(d ->
                js.executeScript("return document.readyState")
                        .equals("complete")
        );
    }

    public static void waitForSeconds(long seconds) {
        try {
            log.info("========= Waiting for " + seconds + " seconds =========");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
