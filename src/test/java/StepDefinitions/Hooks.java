package StepDefinitions;

import Functions.CreateDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Hooks {

    public static WebDriver driver;
    Scenario scenario = null;


    @Before
    public void before(Scenario scenario) { this.scenario = scenario; }

    @Before
    public void initDriver() throws IOException {
        driver = CreateDriver.initConfig();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
