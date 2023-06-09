package apiTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static apiTests.ObjectConverter.*;

public class JacksonDemo {

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        String bmwPayLoad = "{\n" +
                "    \"model\": \"X6\",\n" +
                "    \"color\": \"black\",\n" +
                "    \"engine\": \"4.0\"\n" +
                "}";

        CarPojo bmw = convertJsonToCarClass(bmwPayLoad);
        System.out.println(bmw.getModel());

        CarPojo car = convertJsonToJavaObject(bmwPayLoad, CarPojo.class);
        System.out.println(car.getModel());


        CarPojo audi = new CarPojo("A6", "Red", "2.0");
        String audiPayLoad = convertJavaClassToJson(audi);
        System.out.println(audiPayLoad);

        UserPojo userPojo = new UserPojo("Adrea Boccielli", "Italy", 66, "Florence");
        String userJson = convertJavaClassToJson(userPojo);
        System.out.println(userJson);

        String userJavaClass = "{\"fullName\":\"Adrea Boccielli\",\"citizenship\":\"Italy\",\"age\":66,\"city\":\"Florence\"}";
        UserPojo userJava = convertJsonToJavaObject(userJavaClass, UserPojo.class);
        System.out.println(userJava.getCity());



    }

}
