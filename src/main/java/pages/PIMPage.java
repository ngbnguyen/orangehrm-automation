package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PIMPage extends BasePage {
    public PIMPage (WebDriver driver) {
        super(driver);
    }

    /*------------------ LOCATORS ------------------*/
    /* Add Employee Locators */
    private String PIM_ITEM_TAB_LOCATOR = "//li[contains(@class,'oxd-topbar-body-nav-tab')][contains(normalize-space(.),'%s')]";
    private String PIM_EMPLOYEE_FIRSTNAME_TXT_LOCATOR = "input[name='firstName']";
    private String PIM_EMPLOYEE_MIDDLENAME_TXT_LOCATOR = "input[name='middleName']";
    private String PIM_EMPLOYEE_LASTNAME_TXT_LOCATOR = "input[name='lastName']";
    private String PIM_EMPLOYEE_ID_TXT_LOCATOR = "//label[normalize-space()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input";
    private String PIM_EMPLOYEE_ADD_AVATAR_BTN_LOCATOR = "input[type='file']";
    private String PIM_EMPLOYEE_CREATE_LOGIN_SWITCH_LOCATOR = "//div[@class='oxd-switch-wrapper']";
    private String PIM_EMPLOYEE_USERNAME_TXT_LOCATOR = "//div[contains(@class,'oxd-form-row')]//label[normalize-space()='Username']/following::input[1]";
    private String PIM_EMPLOYEE_PASSWORD_TXT_LOCATOR = "(//div[contains(@class,'oxd-input-group')]//input[@type='password'])[1]";
    private String PIM_EMPLOYEE_CONFIRM_PASSWORD_TXT_LOCATOR = "(//div[contains(@class,'oxd-input-group')]//input[@type='password'])[2]";
    private String PIM_EMPLOYEE_SAVE_BTN  = "//button[.=' Save ']";
    private String PIM_EMPLOYEE_CANCEL_BTN = "//button[.=' Cancel ']";

    /* Edit Employee Locators */
    private String PIM_EMPLOYEE_EDIT_NAME_TXT_LOCATOR = "//div[@class='orangehrm-edit-employee-name']//h6";
    private String PIM_EMPLOYEE_EDIT_ID_TXT_LOCATOR = "//label[normalize-space()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input";
    private String PIM_EMPLOYEE_EDIT_FIELD_TXT_LOCATOR = "//label[normalize-space()=\"%s\"]/ancestor::div[contains(@class,'oxd-input-group')]//input";
    private String PIM_EMPLOYEE_TITTLE_LOCATOR = "//*[normalize-space()='%s']";

    /* Employee list locators */
    private String PIM_LIST_EMPLOYEE_ID_TXT_LOCATOR = "//label[text()='Employee Id']/following::input[1]";
    private String PIM_LIST_SEARCH_BTN_LOCATOR = "//button[normalize-space()='Search']";
    private String PIM_LIST_FIRST_ROW_LOCATOR = ".oxd-table-body .oxd-table-card .oxd-table-row";

    /*------------------ GET ELEMENTS ------------------*/
    private By pimItemTab(String item) {
        return By.xpath(String.format(PIM_ITEM_TAB_LOCATOR,item));
    };
    private By pimEmployeeFirstname = By.cssSelector(PIM_EMPLOYEE_FIRSTNAME_TXT_LOCATOR);
    private By pimEmployeeMiddlename = By.cssSelector(PIM_EMPLOYEE_MIDDLENAME_TXT_LOCATOR);
    private By pimEmployeeLastname = By.cssSelector(PIM_EMPLOYEE_LASTNAME_TXT_LOCATOR);
    private By pimEmployeeId = By.xpath(PIM_EMPLOYEE_ID_TXT_LOCATOR);
    private By pimEmployeeAddAvatorBtn = By.cssSelector(PIM_EMPLOYEE_ADD_AVATAR_BTN_LOCATOR);
    private By pimEmployeeCreatLoginSwitch = By.xpath(PIM_EMPLOYEE_CREATE_LOGIN_SWITCH_LOCATOR);
    private By pimEmployeeUsername = By.xpath(PIM_EMPLOYEE_USERNAME_TXT_LOCATOR);
    private By pimEmployeePassword = By.xpath(PIM_EMPLOYEE_PASSWORD_TXT_LOCATOR);
    private By pimEmployeeConfirmPassword = By.xpath(PIM_EMPLOYEE_CONFIRM_PASSWORD_TXT_LOCATOR);
    private By pimEmployeeSaveBtn = By.xpath(PIM_EMPLOYEE_SAVE_BTN);
    private By pimEmployeeCancelBtn = By.xpath(PIM_EMPLOYEE_CANCEL_BTN);
    private By pimEmployeeEditName = By.xpath(PIM_EMPLOYEE_EDIT_NAME_TXT_LOCATOR);
    private By pimEmployeeEditID = By.xpath(PIM_EMPLOYEE_EDIT_ID_TXT_LOCATOR);
    private By pimEmployeeTitle(String title){
        return By.xpath(String.format(PIM_EMPLOYEE_TITTLE_LOCATOR, title));
    }
    private By pimListEmployeeID = By.xpath(PIM_LIST_EMPLOYEE_ID_TXT_LOCATOR);
    private By pimListSearchBtn = By.xpath(PIM_LIST_SEARCH_BTN_LOCATOR);
    private By pimListFirstRowSearchResult = By.cssSelector(PIM_LIST_FIRST_ROW_LOCATOR);
    private By pimEmployeeEditField(String field){
        return By.xpath(String.format(PIM_EMPLOYEE_EDIT_FIELD_TXT_LOCATOR, field));
    }

    /*------------------ STEPS ------------------*/
    @Step("Click on item tab: {item}")
    public void click_on_item_tab(String item){
        System.out.println("========== Clicking on Tab: " + item + " ==========");
        click(pimItemTab(item));
    }

    @Step("Fill in add Employee's information ")
    public void fill_in_add_employee_information(String firstname, String middlename, String lastname){
        System.out.println("========== Filling in new Employee's Information ==========");
        type(pimEmployeeFirstname, firstname);
        type(pimEmployeeMiddlename, middlename);
        type(pimEmployeeLastname, lastname);
    }

    @Step("Add employee avatar")
    public void add_employee_avatar(String path){
        upload(pimEmployeeAddAvatorBtn, path);
    }
    @Step("Get Employee Id")
    public String get_employee_id(){
        String employeeId = getInputText(pimEmployeeId);
        System.out.println("========== Employee ID is: "+ employeeId + " ==========");
        return employeeId;
    }

    @Step("Turn on Create Login Details switch")
    public void turn_on_create_login_details_switch(){
        click(pimEmployeeCreatLoginSwitch);
    }

    @Step("Fill in Create Login Details information")
    public void fill_in_create_login_details(String username, String password, String confirmPassword){
        type(pimEmployeeUsername, username);
        type(pimEmployeePassword, password);
        type(pimEmployeeConfirmPassword, confirmPassword);
    }

    @Step("Click on Save button")
    public void click_on_save_button(){
        click(pimEmployeeSaveBtn);
    }

    @Step("Get Employee Name in Edit page")
    public String get_Employee_Edit_Name(){
        String employeeEditName = getText(pimEmployeeEditName);
        System.out.println("========== Employee edit name is: "+ employeeEditName + " ==========");
        return employeeEditName;
    }

    @Step("Get Employee ID in Edit page")
    public String get_Employee_Edit_Id(){
        String employeeEditId = getInputText(pimEmployeeEditID);
        System.out.println("========== Employee edit id is: "+ employeeEditId + " ==========");
        return employeeEditId;
    }

    @Step("Wait for title visible: {string}")
    public void wait_for_title_visible(String title){
        waitForVisible(pimEmployeeTitle(title));
    }

    @Step("Click on Search button")
    public void click_on_search_button(){
        click(pimListSearchBtn);
    }

    @Step("Fill in search Employee ID: {string}")
    public void fill_search_employeeID(String employeeID){
        type(pimListEmployeeID, employeeID);
    }

    @Step("Click on first row search result")
    public void click_on_first_row_search_result(){
        scrollToElement(driver, pimListFirstRowSearchResult);
        click(pimListFirstRowSearchResult);
    }

    @Step("Fill in information for field: {string} {value}")
    public void fill_in_information_for_field(String field, String value){
        System.out.println("========= Filling in formation for field: " + field + " , value: " + value + " =========");
        type(pimEmployeeEditField(field), value);
    }

    @Step("Get informatiom from field: {string}")
    public String get_information_from_field(String field){
        String value = getInputText(pimEmployeeEditField(field));
        System.out.println("========= Value of field: " + field + " is: " + value + " =========" );
        return value;
    }
}
