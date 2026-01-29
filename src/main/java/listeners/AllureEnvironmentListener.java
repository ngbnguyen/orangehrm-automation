package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

public class AllureEnvironmentListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {

        try {
            File resultsDir = new File("target/allure-results");
            if (!resultsDir.exists()) {
                resultsDir.mkdirs();
            }

            Properties props = new Properties();
            props.setProperty("Browser", System.getProperty("browser", "Chrome"));
            props.setProperty("Base.URL",
                    "https://opensource-demo.orangehrmlive.com");
            props.setProperty("OS", System.getProperty("os.name"));
            props.setProperty("Java", System.getProperty("java.version"));

            try (FileOutputStream fos = new FileOutputStream(
                    "target/allure-results/environment.properties")) {
                props.store(fos, "Allure Environment");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
