import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestBooks extends BaseTest {

    @Test
    public void bookAddingTest() {

        RestAssured.baseURI = "http://216.10.245.166";
        Response resp = (Response) given()
            .header("Content-Type", "application/json")
            .body("{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\"UnicName\",\n" +
                "\"aisle\":\"227\",\n" +
                "\"author\":\"John foe\"\n" +
                "}\n")
            .when()
            .post("Library/Addbook.php")
            .then().assertThat().statusCode(200)
            .extract().response();
        JsonPath js = rawToJson(resp);
        String id = js.get("ID");
        System.out.println("ID " + id);

    }
}
