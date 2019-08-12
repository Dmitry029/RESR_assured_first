import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class PestPostRequestXml extends BaseTest{

    private Properties prop = new Properties();

    @BeforeTest
    public void getData() throws IOException {
        InputStream fis =
            //new FileInputStream("D:\\Work\\rest_assured1\\src\\main\\resources\\env.properties");
            new FileInputStream("C:\\WorkingDir\\API_tests\\RESR_assured_first\\src\\main\\resources\\env.properties");
        prop.load(fis);
    }


    @Test(description = "testing POST method")
    public void testPostRequest() throws IOException {
        //String postData = GenerateStringFromResource("D:\\Work\\rest_assured1\\src\\main\\resources\\postData.xml");
        String postData = GenerateStringFromResource
                ("C:\\WorkingDir\\API_tests\\RESR_assured_first\\src\\main\\resources\\postData.xml");

        RestAssured.baseURI = prop.getProperty("HOST");

        Response resp = given().
            queryParam("key", prop.getProperty("KEY"))
            .body(postData)
            .when()
            .post(Resources.placePostDataXml())
            .then().assertThat().statusCode(200)
            .and().contentType(ContentType.XML)
            .extract().response()
            ;
        System.out.println(resp.asString());
        XmlPath xmlPath = rawToXml(resp);
        System.out.println(xmlPath.get("response.place_id"));


    }

    public static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
