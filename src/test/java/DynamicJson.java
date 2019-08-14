import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson extends BaseTest {

    @Test(dataProvider = "BookData")
    public void addBook(String isbm, String aisle) {

        RestAssured.baseURI = "http://216.10.245.166";
        Response resp = (Response) given()
            .header("Content-Type", "application/json")
            .body(Payload.addBookBodyJson(isbm, aisle))
            .when()
            .post("Library/Addbook.php")
            .then().assertThat().statusCode(200)
            .extract().response();
        JsonPath js = rawToJson(resp);
        String id = js.get("ID");
        System.out.println("ID " + id);

    }

    @DataProvider(name="BookData")
    public Object[][] getData(){
        return new Object[][]{
                {"firstData","123"},
                {"secondData", "456"},
                {"thirdData", "789"}};
    }
}
