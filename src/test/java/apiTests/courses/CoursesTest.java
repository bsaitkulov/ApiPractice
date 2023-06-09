package apiTests.courses;

import apiTests.BaseApiTest;
import com.digital.entities.Courses;
import com.digital.utils.EntitiesManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CoursesTest extends BaseApiTest {

    Courses courses;

    @BeforeClass(alwaysRun = true)
    public void beforeClass(){
        courses = EntitiesManager.generateCourses();
    }

    @Test
    public void createCourseTest(){
        Courses createdCourse = courseController.createCourse(courses);
        courses.setId(createdCourse.getId());
        courseController.addUserToCourse("15", "124");
        Assert.assertNotNull(createdCourse.getId());
    }
    @Test
    public void getSingleCourseTest(){
        Courses receivedCourse = courseController.getSingleCourse("id", "124");
        Assert.assertEquals(receivedCourse.getId(), "124");
    }
    @Test


    @AfterClass
    public void afterClass(){
        courseController.courseDelete(courses.getId());
    }
}
