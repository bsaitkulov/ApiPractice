package apiTests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerPojo {

    private String name;
    private String club;
    private String position;
    private String nationality;
    private int age;

}
