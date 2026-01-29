package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigUtils;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    private String baseURL = ConfigUtils.get("ui.baseUrl");

    /*------------------ LOCATORS ------------------*/
    private String USERNAME_LOCATOR = "//input[@name='username']";
    private String PASSWORD_LOCATOR = "input[type='password']";
    private String LOGIN_BTN_LOCATOR = "button[type='submit']";

    /*------------------ GET ELEMENTS ------------------*/
    private By username() {
        return By.xpath(USERNAME_LOCATOR);
    }
    private By password() {
        return By.cssSelector(PASSWORD_LOCATOR);
    }
    private By loginBtn() {
        return By.cssSelector(LOGIN_BTN_LOCATOR);
    }

    /*------------------ STEPS ------------------*/
    @Step("Open the URl")
    public void open() {
        openUrl(baseURL);
    }

    @Step("Login with username: {user}")
    public void login(String user, String pass) {
        type(username(), user);
        type(password(),pass);
        click(loginBtn());
    }
}
