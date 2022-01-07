package Functions;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CreateDriver {

    private static String browser;
    private static String os;

    private static final Properties prop = new Properties();
    private static final InputStream in = CreateDriver.class.getResourceAsStream("../test.properties");

    // Initialize Driver Configuration when the class is instanced
    private CreateDriver() throws IOException { CreateDriver.initConfig(); }

    public static WebDriver initConfig() throws IOException {
        WebDriver driver;

        try {
            System.out.println("[ POM Configuration ] - Read the basic properties configuration from ../test.properties");
            prop.load(in);
            browser = prop.getProperty("browser");
            os = prop.getProperty("os");
        } catch (IOException e){
            System.out.println("initConfig Error " + e);
        }

        driver = WebDriverFactory.CreateNewWebDriver(browser, os);

        return driver;

    }

}
