package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.bouncycastle.cms.RecipientId.password;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private String orangeUrl = "https://opensource-demo.orangehrmlive.com/";
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
        openUrl(orangeUrl);
    }

    @Step("Login with username: {user}")
    public void login(String user, String pass) {
//        driver.get(orangeUrl);
//        driver.findElement(username()).sendKeys(user);
//        driver.findElement(password()).sendKeys(pass);
//        driver.findElement(loginBtn()).click();
        type(username(), user);
        type(password(),pass);
        click(loginBtn());
    }
}
