package apiTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Demo1 {

    Faker faker = new Faker();
    String randomFullName = faker.name().fullName();
    String randomEmail = faker.internet().emailAddress();
    String randomGender = faker.options().option("male", "female");
    String randomStatus = faker.options().option("active", "inactive");
    String randomClub = faker.esports().team();
    String randomPosition = faker.options().option("forward", "defender", "midfield", "goalkeeper");
    String randomNationality = faker.nation().nationality();
    int randomAge = faker.number().numberBetween(17, 37);


    @Test
    public void demoTest1() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://reqres.in/api/users/");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.accept(ContentType.JSON);
//        requestSpecification.queryParam("id", "3");
        requestSpecification.pathParam("id", "4");

        Response response = requestSpecification.request(Method.GET, "{id}"); // pathParam()

        response.prettyPrint();
        System.out.println(response.getStatusCode());
        System.out.println(response.getContentType());

    }

    @Test
    public void createNewUserTest() {
        String userPayLoad = "{\n" +
                "    \"name\": \"Alfa Wick\",\n" +
                "    \"email\": \"alfa@gmail.com\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"status\": \"active\"\n" +
                "}";

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://gorest.co.in/public/v2/users");
        requestSpecification.header("Authorization", "Bearer 3bcdcac255f1bc31d0943b7f3786b0ce5ec18a020c454ee66f51ae0c16af0cd7");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.accept(ContentType.JSON);
        requestSpecification.body(userPayLoad);


        Response response = requestSpecification.request(Method.POST);
        response.prettyPrint();


    }

    @Test
    public void createNewUserTest2() {
        String userPayLoad="  {\n" +
                "        \"name\": \"" + randomFullName + "\",\n" +
                "        \"email\": \"" + randomEmail + "\",\n" +
                "        \"gender\": \""+ randomGender + "\",\n" +
                "        \"status\": \"" + randomStatus +"\"\n" +
                "    }";

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://gorest.co.in");
        requestSpecification.header("Authorization", "Bearer 3bcdcac255f1bc31d0943b7f3786b0ce5ec18a020c454ee66f51ae0c16af0cd7");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.accept(ContentType.JSON);
        requestSpecification.body(userPayLoad);

        Response response = requestSpecification.request("POST", "/public/v2/users");
        response.prettyPrint();
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 201);

    }

    @Test
    public void jsonPathTest1() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://reqres.in/api/users/");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.accept(ContentType.JSON);

        Response response = requestSpecification.request(Method.GET);

        JsonPath jsonPath = response.jsonPath();
        int totalUsers = jsonPath.getInt("total");
        String emma = jsonPath.getString("data[2].first_name");
        System.out.println(emma);
        System.out.println(totalUsers);
    }


    @Test
    public void javaToJson() throws JsonProcessingException {
        PlayerPojo playerPojo = new PlayerPojo(randomFullName, randomClub, randomPosition, randomNationality, randomAge);
        String playerJson = ObjectConverter.convertJavaClassToJson(playerPojo);
        System.out.println(playerJson);

    }

    @Test
    public void jsonToJava() throws JsonProcessingException {
        String playerJavaObject = "{\"name\":\"Sid O'Conner\",\"club\":\"Virtus.Pro\",\"position\":\"goalkeeper\",\"nationality\":\"Kazakhs\",\"age\":26}";
        PlayerPojo playerJava = ObjectConverter.convertJsonToJavaObject(playerJavaObject, PlayerPojo.class);
        System.out.println(playerJava.getNationality());
    }

}
