import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StaticJson extends BaseTest {

    @Test
    public void testStaticJson() throws IOException {

        RestAssured.baseURI = "http://216.10.245.166";
        Response resp = (Response) given()
            .header("Content-Type", "application/json")
            .body(GenerateStringFromResource(
                "C:\\WorkingDir\\API_tests\\RESR_assured_first\\src\\main\\resources\\bookDetails.json"))


            .when()
            .post("Library/Addbook.php")
            .then().assertThat().statusCode(200)
            .extract().response();
        JsonPath js = rawToJson(resp);
        String id = js.get("ID");
        System.out.println("ID " + id);


    }
}
