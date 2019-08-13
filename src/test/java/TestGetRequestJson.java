import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestGetRequestJson extends BaseTest {

    @Test(description = "testing GET method")
    public void firstTest() {
        RestAssured.baseURI = "https://maps.googleapis.com";
        Response resp = given().
                param("location", "-33.8670522,151.1957362").
                param("key", "AIzaSyCUuzmM0CkPCDMlxufkIIfgL_N1dRmlPis").
                param("radius", "500").
                log().all().
                when().get("/maps/api/place/nearbysearch/json").
                then().assertThat().statusCode(200)
                //.and().contentType(ContentType.JSON)
                //.and().body("results[0].name", equalTo("Sydney"))
                .and().body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM"))
                .and().header("Server", "scaffolding on HTTPServer2")
                .log().body()
                .extract().response();
        JsonPath jsonPath = rawToJson(resp);
        int count = jsonPath.get("results.size()");
        /*for (int i = 0; i < count; i ++){
            System.out.println(jsonPath.get("results[" + i + "].name"));
        }*/
        System.out.println(jsonPath.get("results.name"));

    }
}
