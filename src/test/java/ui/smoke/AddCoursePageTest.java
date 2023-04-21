package ui.smoke;


import com.digital.ui.pages.courses_page.AddCoursePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.BaseUiTest;

public class AddCoursePageTest extends BaseUiTest{

    AddCoursePage addCoursePage = new AddCoursePage();
    @BeforeClass
    public void openAndAddCourse() {
        addCoursePage.openCoursesPage();
        addCoursePage.addCourseMethod();
    }


//    @Test(description = "C3 - Verify 'Add course' button is displayed")
//    public void test3() {
//        addCoursePage.addCourseMethod();
//    }
//
//    @Test(description = "C4 - Verify input fields are displayed")
//    public void inputFieldsDisplayed() {
//        addCoursePage.fillCourseInputs();
//        addCoursePage.choosePrice();
//    }


    @Test(description = "C6")
    public void testTest() {
        addCoursePage.fillCourseInputs();
        addCoursePage.choosePrice();
        addCoursePage.clickSaveBtn();
    }

    @Test(description = "C6")
    public void test() {
        addCoursePage.fillCourseInputs();
        addCoursePage.choosePrice();
        addCoursePage.clickSaveBtn();
    }

    @Test (description = "C4 - Verify input fields are displayed")
    public void emptyField() {
        addCoursePage.fillCourseInputs();
        addCoursePage.choosePrice();

    }






}
