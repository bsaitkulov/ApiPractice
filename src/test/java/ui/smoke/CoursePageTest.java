package ui.smoke;

import com.digital.ui.pages.courses_page.CoursePage;
import com.digital.ui.pages.HomePage;
import com.digital.ui.pages.LoginPage;
import org.testng.annotations.Test;

public class CoursePageTest {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    CoursePage coursePage = new CoursePage();

    @Test
    public void test() {
        loginPage.openPage();
        loginPage.authorization();
        homePage.goToHomePage();
        homePage.openCoursesPage();
        coursePage.addCourseMethod();
        coursePage.fillCourseInputs();
//        coursePage.clickSaveBtn();

    }




}
