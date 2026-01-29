package stepDefinitions.ui;

import driver.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.DashboardPage;
import pages.PIMPage;

import java.util.Random;
import java.util.UUID;

public class PIMSteps {
    WebDriver driver = DriverFactory.getDriver();
    DashboardPage dashboardPage = new DashboardPage(driver);
    PIMPage pimPage = new PIMPage(driver);
    private String employeeId;
    private String actEmployeeEditName ;
    private String employeeUsername;
    private String employeeDriverLicenseNumber;

    @Given("Get new employee id")
    public void get_newEmployeeId(){
        employeeId = pimPage.get_employee_id();
    }

    @When("User clicks on item tab: {string}")
    public void clickonItemTab(String item){
        pimPage.click_on_item_tab(item);
        pimPage.waitForPageLoad();
        pimPage.waitForSeconds(10);
    }

    @When("User fills in all create new employee's information {string} {string} {string} {string}")
    public void fillInAllCreateNewEmployeeInformation(String firstname, String middlename, String lastname, String avatarPath){
        pimPage.fill_in_add_employee_information(firstname, middlename, lastname);
        pimPage.add_employee_avatar(avatarPath);
    }

    @When("User fills in Create Login Details information {string} {string}")
    public void fillInCreateLoginDetails(String password, String confirmPassword){
        Random random = new Random();
        int number = 1000 + random.nextInt(9000);
        employeeUsername = "User_" + number;
        pimPage.turn_on_create_login_details_switch();
        System.out.println("========== Generated employee username is " + employeeUsername +" ==========");
        pimPage.fill_in_create_login_details(employeeUsername, password, confirmPassword);
    }

    @When("User clicks on Save button")
    public void clickOnSaveButton(){
        pimPage.click_on_save_button();
        pimPage.waitForSeconds(50);
    }

    @Then("Employee ID is displayed")
    public void employeeIdIsDisplayed(){
        Assert.assertNotNull(employeeId);
        Assert.assertTrue(employeeId.length() > 0);
    }

    @Then("Validate employee name is correct {string} {string}")
    public void validateEmployeeNameIsCorrect(String firstname, String lastname){
        actEmployeeEditName = pimPage.get_Employee_Edit_Name();
        String expEmployeeName = firstname + " " + lastname;
        Assert.assertEquals(actEmployeeEditName,expEmployeeName);
        System.out.println("Employee edit name is equal to expected name: " + actEmployeeEditName + ", " + expEmployeeName);
    }

    @Then("Validate employee ID is correct")
    public void validateEmployeeIDIsCorrect(){
        String actEmployeeID = pimPage.get_Employee_Edit_Id();
        Assert.assertEquals(actEmployeeID, employeeId);
        System.out.println("Employee edit ID is equal to expected ID: " + actEmployeeID + ", " + employeeId);
    }

    @When("Wait for title visible: {string}")
    public void waitForTitleVisible(String title){
        pimPage.wait_for_title_visible(title);
    }

    @When("User searches employee with employee ID")
    public void searchEmployeeWithEmployeeID(){
        pimPage.fill_search_employeeID(employeeId);
        pimPage.click_on_search_button();
        pimPage.click_on_first_row_search_result();
        pimPage.waitForSeconds(20);
    }

    @When("User edits Employee - Driver's License Number")
    public void editEmployeeInformationWithField(){
        employeeDriverLicenseNumber = UUID.randomUUID()
                .toString()
                .replaceAll("[^0-9]", "")
                .substring(0, 10);
        pimPage.fill_in_information_for_field("Driver's License Number", employeeDriverLicenseNumber);
    }


    @Then("Validate Driver's License Number is updated correctly")
    public void validateEmployeeDriverLicenseNumberIsUpdatedCorrect(){
        String actEmployeeDriverLicenseNumber = pimPage.get_information_from_field("Driver's License Number");
        Assert.assertEquals(actEmployeeDriverLicenseNumber, employeeDriverLicenseNumber);
        System.out.println("Employee edited Driver's License Number is equal to expected Driver's License Number: " + actEmployeeDriverLicenseNumber + ", " + employeeDriverLicenseNumber);
    }

}
