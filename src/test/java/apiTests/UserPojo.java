package apiTests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPojo {

   private String fullName;
   private  String citizenship;
   private  int age;
   private String  city;

}
