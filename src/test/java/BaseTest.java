import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class BaseTest {

    public XmlPath rawToXml(Response response){
        return new XmlPath(response.asString());
    }

    public JsonPath rawToJson(Response response){
        return new JsonPath(response.asString());
    }
}
