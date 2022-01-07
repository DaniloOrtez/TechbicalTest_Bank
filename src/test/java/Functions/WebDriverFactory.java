package Functions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebDriverFactory {

    private static final Properties prop = new Properties();
    private static final InputStream in = CreateDriver.class.getResourceAsStream( "../test.properties");
    private static String resourceFolder;

    private static WebDriverFactory instance = null;

    public static WebDriver CreateNewWebDriver(String browser, String os) throws IOException {
        WebDriver driver;
        prop.load(in);
        resourceFolder = prop.getProperty("resourceFolder");

        //******** If the driver selected is Firefox  ********//
        if ("FIREFOX".equalsIgnoreCase(browser)) {
            if("WINDOWS".equalsIgnoreCase(os)){
                WebDriverManager.firefoxdriver().setup();
            }
            else{
                System.out.println("The Driver is not selected properly, invalid name: " + browser + ", " + os);
                return null;
            }
            driver = new FirefoxDriver();
        }

        //******** If the driver selected is Chrome  ********//
        else if ("CHROME".equalsIgnoreCase(browser)) {
            if("WINDOWS".equalsIgnoreCase(os)){
                WebDriverManager.chromedriver().setup();
            }
            else{
                System.out.println("The Driver is not selected properly, invalid name: " + browser + ", " + os);
                return null;
            }
            driver = new ChromeDriver();

        }

        //******** if the driver selected is Edge ********//
        else if ("Edge".equalsIgnoreCase(browser)) {
            if ("WINDOWS".equalsIgnoreCase(os)) {
                WebDriverManager.edgedriver().setup();
            }
            else {
                System.out.println("The Driver is not selected properly, invalid name: " + browser + ", " + os);
                return null;
            }
            driver = new EdgeDriver();
        }

        //******** If the driver is not selected  ********//
        else {
            System.out.println("The Driver is not selected properly, invalid name: " + browser + ", " + os);
            return null;
        }

        driver.manage().window().maximize();
        return driver;

    }

}
