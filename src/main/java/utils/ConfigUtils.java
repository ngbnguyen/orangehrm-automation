package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {

    private static final Properties props = new Properties();

    static {
        try (InputStream is = ConfigUtils.class
                .getClassLoader()
                .getResourceAsStream("config/config.properties")) {

            if (is == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }

            props.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Cannot load config.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
