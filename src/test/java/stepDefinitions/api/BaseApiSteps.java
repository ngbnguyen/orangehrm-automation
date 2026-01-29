package stepDefinitions.api;

import api.BaseApi;
import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;

public class BaseApiSteps {

    private Response response;

    @Given("Generate random 4 digits number and store as {string}")
    public void generate4DigitAndStore(String key){
        String value = UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 4);
        TestContext.set(key, value);
        System.out.println("======== Generated " + key + ": " + value + " ========");
    }

    @When("user calls api via json file {string}")
    public void userCallsApi(String jsonFile) {
        response = BaseApi.request(jsonFile);
    }

    @When("store {string} from response to list {string}")
    public void storeValueFromResToList(String jsonPath, String variable){
        response = TestContext.getResponse();
        Object actual = response.jsonPath().get(jsonPath);
        TestContext.set(variable, List.of(String.valueOf(actual)));
        System.out.println("=========== Store " + String.valueOf(actual) + "from response to list " + variable + " ===========");
    }

    @Then("response status should be {int}")
    public void verifyStatus(int status) {
        response.then().statusCode(status);
    }

    @Then("verify response data is not null")
    public void verifyResponseDataIsNotNull(){
        Assert.assertNotNull(response, "❌ API response is NULL ❌");
        Assert.assertNotNull(response.getBody(), "❌ Response body is null ❌");
    }

    @Then("response field should not be null: {string}")
    public void validateResponseFieldNotNull(String field){
        Object actual = response.jsonPath().get("data." + field);

        if (actual instanceof List<?> list) {
            // data là array
            Assert.assertFalse(list.isEmpty(), "List is empty for field: " + field);
            list.forEach(item ->
                    Assert.assertNotNull(item, "Found NULL in list for field: " + field)
            );
        } else {
            // data là object
            Assert.assertNotNull(actual, "Field is NULL: " + field);
        }
    }

    @Then("response field should be null: {string}")
    public void validateResponseFieldNull(String field){
        Object actual = response.jsonPath().get("data." + field);

        if (actual instanceof List<?> list) {
            // data là array
            for (Object item : list) {
                Assert.assertNull(item,
                        "Expected all items in '" + field + "' to be NULL but found: " + item);
            }
        } else {
            // data là object
            Assert.assertNull(actual,
                    "Expected field '" + field + "' to be NULL but was: " + actual);
        }
    }

    @Then("response field {string} should be {string}")
    public void validateFieldEqual(String jsonPath, String expValue){
        response = TestContext.getResponse();
        Object actual = response.jsonPath().get(jsonPath);
        Object expected = TestContext.get(expValue);
        Assert.assertNotNull(actual, "Field not found: " + jsonPath);

        Assert.assertEquals(
                String.valueOf(actual),
                String.valueOf(expected),
                "Mismatch at: " + jsonPath
        );
    }
}
