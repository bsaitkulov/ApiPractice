package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import javax.management.MBeanAttributeInfo;

import static io.restassured.RestAssured.given;


public class ApiMethods {

    public static Response getUserMethod(String baseUrl, String endpoint){
        return given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(endpoint);
    }

    public static Response postUserMethod(String baseUrl,String bearerToken, String userBody, String endpoint){
        return given()
                .baseUri(baseUrl)
                .header("Authorization", bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userBody)
                .post(endpoint);
    }
}
