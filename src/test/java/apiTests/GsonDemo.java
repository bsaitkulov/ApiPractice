package apiTests;

import com.google.gson.Gson;


public class GsonDemo {

    public static void main(String[] args) {
        String bmwPayLoad = "{\n" +
                "    \"model\": \"X6\",\n" +
                "    \"color\": \"black\",\n" +
                "    \"engine\": \"4.0\"\n" +
                "}";

        Gson gson = new Gson();

        CarPojo carPojo = gson.fromJson(bmwPayLoad, CarPojo.class);
        System.out.println(carPojo.getModel());


        CarPojo tesla = new CarPojo("Model S", "White", "Electric");

        String teslaPayLoad = gson.toJson(tesla);
        System.out.println(teslaPayLoad);
    }

}
