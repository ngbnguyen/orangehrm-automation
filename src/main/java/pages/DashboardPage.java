package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends BasePage {
    public DashboardPage(WebDriver driver) {
        super(driver);
    }
    protected static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    /*------------------ LOCATORS ------------------*/
    private String MENU_ITEM_LOCATOR = "a[href*='%s']";
    private String LOADING_SPINNER = ".oxd-form-loader";

    /*------------------ GET ELEMENTS ------------------*/
    private By menuItem(String item){
        return By.cssSelector(String.format(MENU_ITEM_LOCATOR, item));
    }
    private By loadingSpinner = By.cssSelector(LOADING_SPINNER);

    /*------------------ STEPS ------------------*/
    @Step("Click on Menu item: {item}")
    public void clickOnMenuItem(String item){
        click(menuItem(item));
    }

    @Step("Wait for Loading spinner visible")
    public void waitForLoadingSpinnerVisible(){
        log.info("=========== Waiting for Loading spinner visible ===========");
        waitForVisible(loadingSpinner);
    }

    @Step("Wait for Loading spinner disappear")
    public void waitForLoadingSpinnerDisappear(){
        log.info("=========== Waiting for Loading spinner disappear ===========");
        waitForDisappear(loadingSpinner);
    }
}
