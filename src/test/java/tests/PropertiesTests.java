package tests;

import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

public class PropertiesTests {
    @Test
    void testProperties() {
        String environment = System.getProperty("environment");

        System.out.println("Test environment is: "  + environment);
    }

    @Test
    void testNameProperties() {
        String environment = System.getProperty("name");

        System.out.println("My is: "  + environment);
    }

    @Test
    void testBrowserProperties() {
        String browser = System.getProperty("browser", "chrome");

        System.out.println("Browser is: "  + browser);
    }
}
