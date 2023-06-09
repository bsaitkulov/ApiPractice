package apiTests;

import com.digital.api.asserts.ApiAssert;
import com.digital.entities.Courses;
import com.digital.entities.User;
import com.digital.utils.EntitiesManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class IntegrationApiTest extends BaseApiTest{

    User user;
    User expectedUser;
    User actualUser;
    Courses courses;
    Courses actualCourses;
    Courses expectedCourses;

    @BeforeClass(alwaysRun = true)
    public void deleteAllUsers(){
        User [] allUsers = userController.getUsers();
        for (User s : allUsers){
            if (s.getId().equals("1")){
                continue;
            }
            userController.deleteUser(String.valueOf(s.getId()));
        }
    }

    @BeforeTest(alwaysRun = true)
    public void generateTest() {
        expectedUser = EntitiesManager.generateUser();
        expectedCourses = EntitiesManager.generateCourses();
    }

    @Test(priority = 1)
    public void createUser(){
        actualUser = userController.createUser(expectedUser);
        ApiAssert.assertThat(userController.getResponse())
                .isCorrectStatusCode(200)
                .assertUser(actualUser)
                .isCorrectName(expectedUser.getFirstName())
                .isCorrectLastName(expectedUser.getLastName());
    }

    @Test(priority = 2)
    public void createCourse(){
       actualCourses = courseController.createCourse(expectedCourses);
       ApiAssert.assertThat(courseController.getResponse())
               .isCorrectStatusCode(200)
               .assertCourses(actualCourses)
               .isCorrectName(expectedCourses.getName())
               .isCorrectCode(expectedCourses.getCode());

    }

    @Test(priority = 3)
    public void addUserToCourse(){
        courseController.addUserToCourse(actualUser.getId(), actualCourses.getId());
        actualCourses = courseController.getSingleCourse("id", actualCourses.getId());
        System.out.println(actualCourses);
        ApiAssert.assertThat(courseController.getResponse())
                .assertCourses(actualCourses)
                .isUserAddedToCourse(actualUser.getId());


    }

//    @AfterClass(alwaysRun = true)
//    public void deleteCreatedCourse(){
//        courseController.courseDelete(actualCourses.getId());
//    }
}
