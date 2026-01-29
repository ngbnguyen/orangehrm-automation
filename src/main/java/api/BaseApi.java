package api;

import base.BasePage;
import context.TestContext;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.*;
import utils.JsonUtils;

import java.util.Map;

public class BaseApi {

    static {
        RestAssured.baseURI = ConfigUtils.get("api.baseUrl"); // Set baseUrl for the request
    }
    protected static final Logger log = LoggerFactory.getLogger(BaseApi.class);

    public static Response request(String jsonFile) {

        Map<String, Object> apiData = JsonUtils.readJson(jsonFile); // Convert JSON -> Map<String, Object> (dynamic data)

        String method = apiData.get("method").toString();
        String api = apiData.get("api").toString();
        Map<String, Object> body = (Map<String, Object>) apiData.get("body");
        @SuppressWarnings("unchecked")
        Map<String, Object> queryParams =
                (Map<String, Object>) apiData.get("queryParams");

        if (body instanceof Map<?, ?>) {                                                                    // Check whether body is JSON object
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) body;

            map.forEach((k, v) -> {                                                          // Check each field in JSON body
                if (v instanceof String s && s.matches("\\{.+}")) {                                 //Check type {...}
                    String key = s.replace("{", "").replace("}", "");
                    Object ctxValue = TestContext.get(key);                                               //Get value from TestContext
                    if (ctxValue == null) {
                        throw new RuntimeException("Missing TestContext value: " + key);
                    }
                    map.put(k, ctxValue);                                                                 //Replace placeholder by actual value
                }
            });
        }

        var req = RestAssured.given()
                .contentType("application/json");

        // Hardcode Cookie
        String cookieConfig = ConfigUtils.get("api.sessionCookie");
        if (cookieConfig != null) {
            String[] parts = cookieConfig.split("=",2);
            log.info("============ Session Cookie: " + cookieConfig + " ============ ");
            req.cookie(parts[0], parts[1]);
        }

        // Apply query params (GET)
        if (queryParams != null && !queryParams.isEmpty()) {
            req.queryParams(queryParams);
            Allure.addAttachment(
                    "API Request Query Params",
                    "application/json",
                    queryParams.toString(),
                    ".json"
            );
        }

        // Apply body (POST / PUT)
        if (body != null) {
            req.body(body);
            Allure.addAttachment(
                    "API Request Body",
                    "application/json",
                    body.toString(),
                    ".json"
            );
        }


        Response response =  switch (method.toUpperCase()) {
            case "POST" -> req.post(api);
            case "GET" -> req.get(api);
            case "PUT" -> req.put(api);
            case "DELETE" -> req.delete(api);
            default -> throw new RuntimeException("Invalid method");
        };

        TestContext.setResponse(response);
        Allure.addAttachment(
                "API Response Body",
                "application/json",
                response.asPrettyString(),
                ".json"
        );
        return response;
    }
    
}
