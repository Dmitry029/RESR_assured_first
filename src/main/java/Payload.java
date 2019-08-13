public class Payload {

    public static String getPostBody() {
        return "{\n" +
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
    }

    public static String getDeleteBody(String placeId) {
        return "{" +
            "\"place_id\": \"" + placeId + "\"" +
            "}";
    }

    public static String addBookBodyJson(String isbn, String aisle) {
        return "{\n" +
            "\n" +
            "\"name\":\"Learn Appium Automation with Java\",\n" +
            "\"isbn\":\"" + isbn + "\",\n" +
            "\"aisle\":\"" + aisle + "\",\n" +
            "\"author\":\"John foe\"\n" +
            "}\n";
    }
}
