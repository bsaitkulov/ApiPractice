package com.digital.api;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;


@Data
@Slf4j
public abstract class ApiRequest {


    protected  String baseUrl;
    protected String apiKey;
    private  String SLASH = "/";
    protected Header header;
    protected RequestSpecification spec;
    protected Response response;

    public ApiRequest(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.spec = given()
                .auth()
                .basic(this.apiKey, "")
                .baseUri(this.baseUrl)
                .relaxedHTTPSValidation();

    }
    public int getStatusCode(){
      return this.response.getStatusCode();


    }

    public Response get(String endpoint){
        log.info("Performed GET: {}", endpoint);
      this.response = given()
              .spec(this.spec)
//              .header(this.header)
                .get(endpoint);
              logResponse();
      return this.response;
    }

    public Response post(String endpoint, String body){
        log.info("Performed POST: {}", endpoint);
        log.info("Body is: {}", body);
        this.response = given()
                .contentType(ContentType.URLENC)
                .spec(this.spec)
                .body(body)
                .post(endpoint);
                logResponse();
        return this.response;
    }
    public Response post(String endPoint, Map<String, String> formParams) {
        log.info("Performed POST {}", endPoint);
        log.info("Form params is {}", formParams);
        this.response = given()
                .spec(this.spec)
                .formParams(formParams)
                .post(endPoint);
        logResponse();
        return this.response;
    }

    public Response signUpBody(String firstName, String lastName, String email, String login, String password) {
        return this.response;
    }
//    public Response postDelete(String endpoint, String key, String value){
//        logger.info("Performed POST {}", endpoint);
//        logger.info("Body is {}", key);
//        this.response = given()
//                .spec(this.spec)
//                .multiPart(key, value)
//                .post(endpoint);
//                logResponse();
//                return this.response;
//    }

    public static String formatParameters(HashMap<String, String> parameters) {
        StringBuilder query = new StringBuilder("?");
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            query.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        return query.deleteCharAt(query.length() - 1).toString();
    }
    public void logResponse() {
        log.warn("Response is: {}", this.response.getBody().asPrettyString());
        log.warn("Status code is: {}", this.response.getStatusCode());
    }

    public String generateEndpoints(String ...args){
        StringBuilder result = new StringBuilder();
        for (String arg : args){
            result.append(arg).append(SLASH);
        }
        return result.substring(0, result.length()-1);
    }

    public Map<String, String> pathParam(String key, String value){
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(){{
            put(key,value);
        }};
        return linkedHashMap;
    }

}