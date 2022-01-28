package StepDefinitions;

import Functions.SeleniumFunctions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileNotFoundException;
import java.io.IOException;

public class StepsDefinitions {

    WebDriver driver;
    SeleniumFunctions functions = new SeleniumFunctions();

    public StepsDefinitions() {
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


    @And("^I (.*) data of (.*)$")
    public void sendText(String element, String text) throws Exception {
        By SeleniumElement = functions.getCompleteElement(element);
        driver.findElement(SeleniumElement).clear();
        driver.findElement(SeleniumElement).sendKeys(text);
    }


    @And("^I select the (.*): (.*)$")
    public void iSelectMenu(String element, String Name) throws Exception {
        functions.ClickOnElment(element, Name);
    }


    @And("^I compare the (.*) web (.*) whit the data from the (.*)$")
    public void iPrintTheContend(String value, String element, String URL) throws Exception {
        functions.getPokemonData(value, element, URL);
    }

    @And("^I do click in (.*) Pokemon")
    public void iDoClick(String element) throws Exception {
        functions.ClickJSElement(element);
    }

    //Mortgage steps ******************************************************************************************

    @And("^I select (.*)")
    public void iSelectMenu(String element) throws Exception {
        functions.ClickJSElement(element);
    }

    @And("^The (.*) is completed with the (.*)$")
    public void sendJsonTextValue(String element, String JsonText) throws Exception {
        By SeleniumElement = functions.getCompleteElement(element);
        driver.findElement(SeleniumElement).clear();

        String Text = functions.getEntityValue(JsonText);
        driver.findElement(SeleniumElement).sendKeys(Text);
        //Thread.sleep(3000);
    }

    @And("^I do scroll to (.*)$")
    public void iDoScrollTo(String element) throws Exception {
        functions.scrollToElement(element);
    }

    @And("^I set the (.*) dropdrown with the (.*)$")
    public void iSetDropdrown(String element, String JsonValue) throws Exception {
        Select opt = functions.selectOption(element);
        String value = functions.getEntityValue(JsonValue);
        opt.selectByVisibleText(value);
    }

    @And("^I (.*) the (.*) result$")
    public void iTheTotalResult(String element_1, String element_2) throws Exception {
        functions.TakeDataElement(element_1, element_2);
    }



}
