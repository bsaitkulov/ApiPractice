package apiTests;


import com.digital.api.asserts.ApiAssert;
import com.digital.entities.User;
import com.digital.utils.EntitiesManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class UserTest extends BaseApiTest{

    User user;
    User expectedUser;
    User actualUser;
    @BeforeClass(alwaysRun = true)
    public void beforeClass(){
        user = EntitiesManager.generateUser();
    }

//    @Test
//    public void testUsers(){
//        User createdUser = userController.createUser(user);
//        User receivedUser = userController.receiveSingleUser("id", createdUser.getId());
//        user.setId(createdUser.getId());
////        userController.getResponse();
//        Assert.assertNotNull(receivedUser);
//    }
    @Test
    public void testUsers() {
        actualUser = userController.createUser(expectedUser);
        ApiAssert.assertThat(userController.getResponse())
                .isCorrectStatusCode(200)
                .assertUser(actualUser)
                .isCorrectName(expectedUser.getFirstName())
                .isCorrectLastName(expectedUser.getLastName());
    }

    @Test
    public void getUsersTest(){
        User [] allUsers = userController.getUsers();
        for (User s : allUsers){
            System.out.println(s.getId());
        }
    }
    @AfterClass(alwaysRun = true)
    public void cleanUp() {
        userController.deleteUser(actualUser.getId());
    }


//    @AfterClass(alwaysRun = true)
//    public void afterClass(){
//        userController.userDelete(user.getId());
//    }
}
