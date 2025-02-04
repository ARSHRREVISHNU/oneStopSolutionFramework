package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.PropertyReader;

import static io.restassured.RestAssured.*;

public class WeatherApiStepDefinitions {
    private Response response;
    private String baseUrl = PropertyReader.getProperty("base.url");
    private String apiKey = PropertyReader.getProperty("api.key");

    @Given("the Weather API is available")
    public void the_weather_api_is_available() {
        RestAssured.baseURI = baseUrl;
    }

    @When("I request the current weather for {string}")
    public void i_request_the_current_weather_for_city(String city) {
        response = given()
                .header("Content-Type", "application/json")
                .queryParam("q", city)
                .queryParam("key", apiKey)
                .when()
                .get();
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response should contain the city {string}")
    public void the_response_should_contain_the_city(String city) {
        Assert.assertTrue(response.asString().contains(city));
    }
}
