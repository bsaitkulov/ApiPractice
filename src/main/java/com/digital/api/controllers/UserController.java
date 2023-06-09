package com.digital.api.controllers;

import com.digital.api.ApiRequest;
import com.digital.entities.User;
import com.digital.entities.UserDelete;
import com.digital.entities.UserEdit;
import com.digital.entities.UserSignUp;
import com.digital.utils.EntitiesManager;
import com.digital.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

import static com.digital.api.ObjectConverter.convertJavaClassToJson;
import static com.digital.api.TalentLMSBaseEndpoints.*;


public class UserController extends ApiRequest {

    Faker faker = new Faker();

    String randomFirstName = faker.name().firstName();
    String randomLastName = faker.name().lastName();
    String randomEmail = faker.internet().emailAddress();
    String randomLogin = faker.name().username();
    String randomPassword = faker.internet().password();

    public UserController(String baseUrl, String apiKey) {
        super(baseUrl, apiKey);
    }
    public User[] getUsers(){
       return super.get(generateEndpoints(API, V1, USERS)).as(User[].class);
    }

    public Response userSignUp() throws JsonProcessingException {
        UserSignUp userSignUp = new UserSignUp(randomFirstName, randomLastName, randomEmail, randomLogin, randomPassword);
        String userSignUpJson = convertJavaClassToJson(userSignUp);
        return super.post(generateEndpoints(API,V1,SIGNUP), userSignUpJson);

    }
    public User createUser(User user){
        String userJson = JsonUtils.convertJavaObjectToJson(user);
        return super.post(generateEndpoints(API,V1,SIGNUP),userJson).as(User.class);
    }


    public Response userEdit() {
        return super.post(generateEndpoints(API,V1,EDIT_USER), "user_id=8&first_name=Abrakadabra&last_name=Dumbledore");
    }

    public Response userDelete(String id) {
        return super.post(generateEndpoints(API,V1,DELETE_USER), "user_id="+id);
    }

    public Response deleteUser(String userId) {
        Map<String, String> formParams = new HashMap<>() {{
            put("user_id", userId);
            put("deleted_by_user_id", "1");
        }};
        return super.post(generateEndpoints(API, V1, DELETE_USER), formParams);
    }

    public Response userLogin() {
        return super.post(generateEndpoints(API,V1,USER_LOGIN), "login=michel.harris&password=py27vb1w8fi");
    }

    public User isUserOnline() {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", "20");
        return super.get(generateEndpoints(API, V1, IS_USER_ONLINE, formatParameters(map))).as(User.class);
    }
    public User receiveSingleUser(String by, String idOrEmail) {
        HashMap<String, String> map = new HashMap<>();
        map.put(by,idOrEmail);
        return get(generateEndpoints(API, V1, USERS, formatParameters(map))).as(User.class);
    }

    public static void main(String[] args) throws JsonProcessingException {
        UserController userController = new UserController("https://bravos.talentlms.com", "pPLKsrpvy4hz7rQAe5V5v3wCg3jT4T");
//        User user = EntitiesManager.generateUser();
//        userController.createUser(user);
//        Arrays.stream(userController.receiveUsers()).forEach(s -> System.out.println(s.getFirstName()));
//        Assert.assertEquals(userController.getStatusCode(), 200);
//        userController.receiveSingleUser("id", "1");
        userController.userLogin();
        userController.isUserOnline();




    }
}
