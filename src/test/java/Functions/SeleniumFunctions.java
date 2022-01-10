package Functions;

import StepDefinitions.Hooks;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SeleniumFunctions {

    WebDriver driver;

    public SeleniumFunctions(){
        driver = Hooks.driver;
    }

    // Json Page file path
    public static String PageFilePath = "src/test/resources/Pages/";
    public static String FileName = "";

    // Scenario Test Data
    public static String ElementText = "";
    public static List<String> apiPokemonDataResponse;

    // test properties config
    private static final Properties prop = new Properties();
    private static final InputStream in = CreateDriver.class.getResourceAsStream( "../test.properties");

    // Entities of DOM pages - Json
    public static String GetFieldBy = "";
    public static String ValueToFind = "";


    //******************************************************************************************************************
    //******************************************* WEB FUNCTIONS ********************************************************
    //******************************************************************************************************************

    // Read Json file
    @org.jetbrains.annotations.Nullable
    public static Object readJson() throws FileNotFoundException {
        FileReader reader = new FileReader(PageFilePath + FileName);
        try{
            JSONParser jsonParser = new JSONParser();
            return jsonParser.parse(reader);
        }catch (NullPointerException| IOException | ParseException e){
            System.out.println("ReadEntity: No existe el archivo " + FileName);
            throw new IllegalStateException("ReadEntity: No existe el archivo " + FileName, e);
        }
    }
    //******************************************************************************************************************

    // Read the property file
    public String readProperties(String property) throws IOException {
        prop.load(in);
        return prop.getProperty(property);
    }
    //******************************************************************************************************************

    // Read entity into the Jason file
    public static @NotNull JSONObject ReadEntity(String element) throws Exception {
        JSONObject Entity;
        JSONObject jsonObject = (JSONObject) readJson();
        assert jsonObject != null;
        Entity = (JSONObject) jsonObject.get(element);
        return Entity;
    }
    //******************************************************************************************************************

    // Get element of the entity in the Jason file
    public static By getCompleteElement(String element) throws Exception {
        By result = null;
        JSONObject Entity = ReadEntity(element);

        GetFieldBy = (String) Entity.get("GetFieldBy");
        ValueToFind = (String) Entity.get("ValueToFind");

        if ("className".equalsIgnoreCase(GetFieldBy)) {
            result = By.className(ValueToFind);
        } else if ("cssSelector".equalsIgnoreCase(GetFieldBy)) {
            result = By.cssSelector(ValueToFind);
        } else if ("id".equalsIgnoreCase(GetFieldBy)) {
            result = By.id(ValueToFind);
        } else if ("linkText".equalsIgnoreCase(GetFieldBy)) {
            result = By.linkText(ValueToFind);
        } else if ("name".equalsIgnoreCase(GetFieldBy)) {
            result = By.name(ValueToFind);
        } else if ("link".equalsIgnoreCase(GetFieldBy)) {
            result = By.partialLinkText(ValueToFind);
        } else if ("tagName".equalsIgnoreCase(GetFieldBy)) {
            result = By.tagName(ValueToFind);
        } else if ("xpath".equalsIgnoreCase(GetFieldBy)) {
            result = By.xpath(ValueToFind);
        }
        return result;

    }
    //******************************************************************************************************************

    // Click Function in a specific element in the DOM
    public void ClickJSElement(String element) throws Exception {
        By SeleniumElement = SeleniumFunctions.getCompleteElement(element);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", driver.findElement(SeleniumElement));
    }
    //******************************************************************************************************************

    // Read the entity 'ValueToFind' including in the Jason File
    public static String getEntityValue(String element) throws Exception {
        JSONObject Entity = ReadEntity(element);
        ValueToFind = (String) Entity.get("ValueToFind");
        return ValueToFind;
    }
    //******************************************************************************************************************

    // Catch y return the text from a web object in the DOM given an editable xpath
    public String getObjectText(String xpath){
        ElementText = driver.findElement(By.xpath(xpath)).getText();
        return ElementText;
    }
    //******************************************************************************************************************


    // Read the DOM information and return the API data to compare results
    public void getPokemonData(String valueToFind, String element, String URL) throws Exception {
        String foundValue;
        int rowCount;
        String CompleteXpathElement;
        List<String> objectWebList = new ArrayList<>();

        String baseXpath = SeleniumFunctions.getEntityValue(element);
        String apiBaseURL = SeleniumFunctions.getEntityValue(URL);

        switch (element) {
            case "stats" -> {
                rowCount = driver.findElements(By.xpath(baseXpath + "//tr")).size();
                for (int i = 2; i < rowCount; i++) {
                    CompleteXpathElement = baseXpath + "//tr[" + i + "]//th";
                    foundValue = getObjectText(CompleteXpathElement).replace(":", "");

                    if (foundValue.equals("Sp. Atk")) {
                        foundValue = "special attack";
                    } else if (foundValue.equals("Sp. Def")) {
                        foundValue = "special defense";
                    }
                    objectWebList.add(foundValue.toLowerCase());
                }
                apiPokemonDataResponse = apiFunctions.readAllDetails(valueToFind, element, apiBaseURL);
            }
            case "abilities" -> {
                rowCount = driver.findElements(By.xpath(baseXpath)).size();
                for (int i = 1; i <= rowCount; i++) {
                    CompleteXpathElement = baseXpath + "[" + i + "]";
                    foundValue = getObjectText(CompleteXpathElement).toLowerCase();
                    objectWebList.add(foundValue);
                }
                apiPokemonDataResponse = apiFunctions.readAllDetails(valueToFind, element, apiBaseURL);
            }

            default -> System.out.println("The options doesn't exist");
        }
        apiPokemonDataResponse = apiFunctions.readAllDetails(valueToFind, element, apiBaseURL);
        Assert.assertEquals("The data is no equal ", objectWebList, apiPokemonDataResponse);
        System.out.println("The pokemon WEB stats are: " + objectWebList);
        System.out.println("The pokemon API stats are: " + apiPokemonDataResponse);

    }
    //******************************************************************************************************************




}
