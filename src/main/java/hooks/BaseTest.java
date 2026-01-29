package hooks;

import io.cucumber.java.Before;

import java.util.Map;

public class BaseTest {

    private static final ThreadLocal<Map<String, Object>> testData = new ThreadLocal<>();

    public static void setData(Map<String, Object> data) {
        testData.set(data);
    }

    public static Map<String, Object> getData() {
        return testData.get();
    }

    @Before
    public void beforeScenario() {
        // This ensures ThreadLocal is clean per scenario
        testData.remove();
    }
}
