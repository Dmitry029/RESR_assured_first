import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestPostRequest {
    private String SAMPLE_BODY = "{\n" +
        "    \"location\":{\n" +
        "        \"lat\" : -38.383494,\n" +
        "        \"lng\" : 33.427362\n" +
        "    },\n" +
        "    \"accuracy\":50,\n" +
        "    \"name\":\"Frontline house\",\n" +
        "    \"phone_number\":\"(+91) 983 893 3937\",\n" +
        "    \"address\" : \"29, side layout, cohen 09\",\n" +
        "    \"types\": [\"shoe park\",\"shop\"],\n" +
        "    \"website\" : \"http://google.com\",\n" +
        "    \"language\" : \"French-IN\"\n" +
        "}\n";

    @Test(description = "testing POST method")
    public void testPostRequest() {
        RestAssured.baseURI = "http://216.10.245.166";
        given().
            queryParam("key", " qaclick123")
            .body(SAMPLE_BODY)
            .when()
            .post("/maps/api/place/add/json")
            .then().assertThat().statusCode(200)
            .and().contentType(ContentType.JSON)
            .and().body("status", equalTo("OK"));
    }

    @Test(description = "create and delete")
    public void testCreateAndDelete() {
        RestAssured.baseURI = "http://216.10.245.166";
        // get the responce
        Response res = given().
            queryParam("key", " qaclick123")
            .body(SAMPLE_BODY)
            .when()
            .post("/maps/api/place/add/json")
            .then().extract().response();
        System.out.println(res.asString());
        // get th eplace Id
        JsonPath js = new JsonPath(res.asString());
        String placeId = js.get("place_id");
        System.out.println("Place Id -" + placeId);
        // place the place id into delete request
        given()
            .queryParam("key", " qaclick123")
            .body("{" +
                "\"place_id\": \"" + placeId + "\"" +
                "}")
            .when().post("/maps/api/place/delete/json")
            .then().assertThat().statusCode(200)
            .and().contentType(ContentType.JSON)
            .and().body("status", equalTo("OK"));
    }
}
