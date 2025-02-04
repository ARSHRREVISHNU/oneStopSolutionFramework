package stepdefinitions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class CatApiTest {
    public static void main(String[] args) {
        // Base URI
        RestAssured.baseURI = "https://api.thecatapi.com/v1/images/search";
        
        // Make the GET request
        Response response = given()
            .header("Content-Type", "application/json")
            .header("x-api-key", "live_uKCyA1Rq9Z91vGR7cE7QBdMscyjtvAFYmylnY520ys2fZK5dfMdhp7oWLMxnXrbA")
            .queryParam("size", "med")
            .queryParam("mime_types", "jpg")
            .queryParam("format", "json")
            .queryParam("has_breeds", true)
            .queryParam("order", "RANDOM")
            .queryParam("page", 0)
            .queryParam("limit", 1)
        .when()
            .get()
        .then()
            .statusCode(200) // Validate response status
            .extract().response();
        
        // Print the response
        System.out.println("Response: " + response.asString());
    }
}
