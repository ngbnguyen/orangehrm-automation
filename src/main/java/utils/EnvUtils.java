package utils;

public class EnvUtils {

    public static String getUsername() {
        String username = System.getProperty("ORANGE_USERNAME");
        return username;

    }

    public static String getPassword() {
        String password = System.getProperty("ORANGE_PASSWORD");
        return password;
    }
}
