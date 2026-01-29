package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.Map;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();  // Lib to Convert JSON ⇄ Map
    public static Map<String, Object> readJson(String path) {
        try (InputStream is =
                     JsonUtils.class
                             .getClassLoader()
                             .getResourceAsStream(path)) {

            if (is == null)
                throw new RuntimeException("============== Cannot find file: " + path + " ==============");

            return mapper.readValue(is, Map.class);  //Convert JSON → Map

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
