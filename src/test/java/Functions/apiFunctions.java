package Functions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class apiFunctions {

    public static String valueInside;
    public static String valorName;
    public static String endPoint;
    public static JSONObject dataObjects;
    public static JSONObject object;
    public static List<String> apiData = new ArrayList<>();
    public static List<String> apiDataList = new ArrayList<>();
    public static JSONArray array = new JSONArray();
    public static JSONParser parse = new JSONParser();
    public static Response response;
    public static String responseBody;

    //Function to read the data from the API
    public static List<String> readAllDetails(String valueToFind, String element, String baseURL) throws ParseException {

        apiData.clear();

        switch (element) {
            case "stats" -> {
                valueInside = "stat";
                valorName = "name";
                endPoint = "pokemon";
                JSONArray statsArray = getApiJsonArray(baseURL, endPoint, valueToFind, element);
                apiData = listApiData(statsArray, valueInside, valorName);
            }
            case "abilities" -> {
                valueInside = "ability";
                valorName = "name";
                endPoint = "pokemon";
                JSONArray abilitiesArray = getApiJsonArray(baseURL, endPoint, valueToFind, element);
                apiData = listApiData(abilitiesArray, valueInside, valorName);
            }
            case "types" -> {
                valueInside = "type";
                valorName = "name";
                endPoint = "pokemon";
                JSONArray abilitiesArray = getApiJsonArray(baseURL, endPoint, valueToFind, element);
                apiData = listApiData(abilitiesArray, valueInside, valorName);
            }

            default -> System.out.println("Then options doesn't exist in the api response");
        }
        return apiData;

    }
    //******************************************************************************************************************

    //Return a data list with the values from the API given a specific value to find replacing some characters
    public static List<String> listApiData(JSONArray jsonArray, String vInside, String take){
        array.clear();
        String statName;
        Object statValue;
        String value;
        for (int i = 0; i < jsonArray.size(); i++) {
            dataObjects = (JSONObject) jsonArray.get(i);
            if (vInside.equals("stat")){
                array.add(dataObjects.get(vInside));
                object = (JSONObject) array.get(i);
                statName = (String) object.get(take);
                statValue = dataObjects.get("base_stat");
                value = statName.replace("-", " ") + ": " + statValue;
            } else {
                array.add(dataObjects.get(vInside));
                object = (JSONObject) array.get(i);
                value = (String) object.get(take);
            }
            apiDataList.add(value.toLowerCase().replace("-", " "));
        }
        return apiDataList;
    }
    //******************************************************************************************************************


    //Return an array with the values from the API given a specific value to find
    public static JSONArray getApiJsonArray(String baseURL, String endPoint, String valueToFind, String element) throws ParseException {
        response = RestAssured.get(baseURL+ endPoint+ "/" + valueToFind); // request the server building the complete URL
        responseBody = response.getBody().asString(); // store the response body in string
        JSONObject apiObjects = (JSONObject) parse.parse(responseBody); // Convert response body in a Json Object
        JSONArray effectA = (JSONArray) apiObjects.get(element);
        return effectA;
    }
    //******************************************************************************************************************




}