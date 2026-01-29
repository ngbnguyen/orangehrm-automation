package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    /*------------------ LOCATORS ------------------*/
    private String MENU_ITEM_LOCATOR = "a[href*='%s']";

    /*------------------ GET ELEMENTS ------------------*/
    private By menuItem(String item){
        return By.cssSelector(String.format(MENU_ITEM_LOCATOR, item));
    }

    /*------------------ STEPS ------------------*/
    @Step("Click on Menu item: {item}")
    public void clickOnMenuItem(String item){
        click(menuItem(item));
    }
}
