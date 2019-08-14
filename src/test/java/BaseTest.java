import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseTest {

    public XmlPath rawToXml(Response response){
        return new XmlPath(response.asString());
    }

    public JsonPath rawToJson(Response response){
        return new JsonPath(response.asString());
    }

    public static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
