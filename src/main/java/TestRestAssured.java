import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class TestRestAssured {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://maps.googleapis.com";
        given().
            param("location", "-33.8670522,151.1957362").
            param("key", "AIzaSyCUuzmM0CkPCDMlxufkIIfgL_N1dRmlPis").
            param("radius", "500").
            when().get("/maps/api/place/nearbysearch/json").
            then().assertThat().statusCode(200);
    }

}
