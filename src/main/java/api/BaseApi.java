package api;

import context.TestContext;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.*;
import utils.JsonUtils;

import java.util.Map;

public class BaseApi {

    static {
        RestAssured.baseURI = ConfigUtils.get("api.baseUrl");
    }


    public static Response request(String jsonFile) {

        Map<String, Object> apiData = JsonUtils.readJson(jsonFile);

        String method = apiData.get("method").toString();
        String api = apiData.get("api").toString();
        Map<String, Object> body = (Map<String, Object>) apiData.get("body");
        @SuppressWarnings("unchecked")
        Map<String, Object> queryParams =
                (Map<String, Object>) apiData.get("queryParams");

        if (body instanceof Map<?, ?>) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) body;

            map.forEach((k, v) -> {
                if (v instanceof String s && s.matches("\\{.+}")) {
                    String key = s.replace("{", "").replace("}", "");
                    Object ctxValue = TestContext.get(key);
                    if (ctxValue == null) {
                        throw new RuntimeException("Missing TestContext value: " + key);
                    }
                    map.put(k, ctxValue);
                }
            });
        }

        var req = RestAssured.given()
                .contentType("application/json");

        // ✅ HARDCODE COOKIE
        String cookieConfig = ConfigUtils.get("api.sessionCookie");
        if (cookieConfig != null) {
            String[] parts = cookieConfig.split("=",2);
            System.out.println("============ Session Cookie: " + cookieConfig + " ============ ");
            req.cookie(parts[0], parts[1]);
        }

        // Apply query params (GET)
        if (queryParams != null && !queryParams.isEmpty()) {
            req.queryParams(queryParams);
        }

        // Apply body (POST / PUT)
        if (body != null) {
            req.body(body);
        }


        Response response =  switch (method.toUpperCase()) {
            case "POST" -> req.post(api);
            case "GET" -> req.get(api);
            case "PUT" -> req.put(api);
            case "DELETE" -> req.delete(api);
            default -> throw new RuntimeException("Invalid method");
        };

        TestContext.setResponse(response);
        return response;
    }
}
