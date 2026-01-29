package hooks;

import context.TestContext;
import io.cucumber.java.After;

public class ApiHooks {

    @After("@api")
    public void cleanUp() {
        TestContext.clearAll();
    }
}
