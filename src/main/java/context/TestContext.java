package context;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private static final Map<String, Object> data = new HashMap<>();
    private static final ThreadLocal<Response> response = new ThreadLocal<>();

    public static void set(String key, Object value) {
        data.put(key, value);
    }

    public static Object get(String key) {
        return data.get(key);
    }

    public static Map<String, Object> getAll() {
        return data;
    }

    public static void setResponse(Response res) {
        response.set(res);
    }

    public static Response getResponse() {
        return response.get();
    }

    public static void clearResponse() {
        response.remove();
    }

    public static void clearData() {
        data.clear();
    }

    public static void clearAll() {
        clearData();
        clearResponse();
    }
}
