import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestPostDeleteRequestJson {
    private Properties prop = new Properties();

    @BeforeTest
    public void getData() throws IOException {
        InputStream fis =
            //new FileInputStream("D:\\Work\\rest_assured1\\src\\main\\resources\\env.properties");
            new FileInputStream("C:\\WorkingDir\\API_tests\\RESR_assured_first\\src\\main\\resources\\env.properties");
        prop.load(fis);
    }


    @Test(description = "testing POST method")
    public void testPostRequest() {
        RestAssured.baseURI = prop.getProperty("HOST");

        given().
            queryParam("key", prop.getProperty("KEY"))
            .body(Payload.getPostBody())
            .when()
            .post(Resources.placePostData())
            .then().assertThat().statusCode(200)
            .and().contentType(ContentType.JSON)
            .and().body("status", equalTo("OK"));
    }

    @Test(description = "create and delete")
    public void testCreateAndDelete() {
        RestAssured.baseURI = prop.getProperty("HOST");
        // get the response
        Response res = given().
            queryParam("key", prop.getProperty("KEY"))
            .body(Payload.getPostBody())
            .when()
            .post(Resources.placePostData())
            .then().extract().response();
        System.out.println(res.asString());
        // get the place Id
        JsonPath js = new JsonPath(res.asString());
        String placeId = js.get("place_id");
        System.out.println("Place Id -" + placeId);
        // place the place id into delete request
        given()
            .queryParam("key", prop.getProperty("KEY"))
            .body(Payload.getDeleteBody(placeId))
            .when().post(Resources.placeDeleteData())
            .then().assertThat().statusCode(200)
            .and().contentType(ContentType.JSON)
            .and().body("status", equalTo("OK"));
    }
}
