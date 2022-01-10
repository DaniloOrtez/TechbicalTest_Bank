package StepDefinitions;

import Functions.SeleniumFunctions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.io.IOException;

public class StepsDefinitions {

    WebDriver driver;
    SeleniumFunctions functions = new SeleniumFunctions();

    public StepsDefinitions() throws Exception {
        driver = Hooks.driver;
    }

    @Given("^I go to the (.*) site$")
    public void iGoToTheMainSite(String URL) throws IOException {
        String url = functions.readProperties(URL);
        driver.get(url);
    }


    @Then("^The (.*) DOM information is loaded$")
    public void theDOMInformationIsLoaded(String json) throws FileNotFoundException {
        SeleniumFunctions.FileName = json;
        functions.readJson();
    }


    @And("^I set the (.*) with the value (.*)$")
    public void sendText(String element, String text) throws Exception {
        By SeleniumElement = functions.getCompleteElement(element);
        driver.findElement(SeleniumElement).clear();
        driver.findElement(SeleniumElement).sendKeys(text);
    }


    @And("^I select (.*)")
    public void iSelectMenu(String element) throws Exception {
        functions.ClickJSElement(element);
        //Thread.sleep(6000);
    }


    @And("^I compare the (.*) web (.*) whit the data from the (.*)$")
    public void iPrintTheContend(String value, String element, String URL) throws Exception {
        functions.getPokemonData(value, element, URL);
    }





}
