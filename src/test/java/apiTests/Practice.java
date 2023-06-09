package apiTests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;


public class Practice {

    Faker faker = new Faker();
    String randomFullName = faker.name().fullName();
    String randomEmail = faker.internet().emailAddress();
    String randomGender = faker.options().option("male", "female");
    String randomStatus = faker.options().option("active", "inactive");


    @Test
    public void getUserTest(){

        Response response = ApiMethods.getUserMethod("https://gorest.co.in", "public/v2/users");

        response.prettyPrint();

        response.then()
                .statusCode(200);
    }

    @Test
    public void postUserTest(){

        String bearerToken = "Bearer 3bcdcac255f1bc31d0943b7f3786b0ce5ec18a020c454ee66f51ae0c16af0cd7";
        String userPayLoad="  {\n" +
                "        \"name\": \"" + randomFullName + "\",\n" +
                "        \"email\": \"" + randomEmail + "\",\n" +
                "        \"gender\": \""+ randomGender + "\",\n" +
                "        \"status\": \"" + randomStatus +"\"\n" +
                "    }";
   Response response = ApiMethods.postUserMethod("https://gorest.co.in", bearerToken, userPayLoad, "public/v2/users");

        response.prettyPrint();

        response.then()
           .statusCode(201)
           .body("name", equalTo(randomFullName));

    }
}
