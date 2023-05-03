package ui.smoke;
import com.digital.annotations.TestCase;
import com.digital.ui.driver.Driver;
import com.digital.ui.pages.UploadFilesVenera;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

@Epic("Group")
@Feature("Uploading functionality")

public class UploadFilesTest {
    UploadFilesVenera groups = new UploadFilesVenera();
    String textParam = "lu";
    WebDriver driver = Driver.getDriver();

    @BeforeClass
    public void setUp() throws InterruptedException {
        groups.openPage().authorization();
        groups.enterGroup()
                .enterCourse()
                .enterFiles();
    }
    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    @Test(description = "Verify user can add files in group" ,priority = 1)
    @TestCase(id = 8)
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Venera")
    @Story("As a user i should be able to upload files")
    @Link(name = "requirements",url = "https://venera.talentlms.com/index")

    public  void uploadFileTest() throws InterruptedException {
                groups.uploadFile("C:\\Users\\eliza\\Downloads\\Nature.jpeg")
                .assertTest();
    }
    @Test(priority = 2)
    public void updateFileNameTest() throws InterruptedException {
                groups.editMethod(textParam);
        Thread.sleep(3000);
        driver.navigate().refresh();
        String actualText = driver.findElement(By.xpath("//span[@class='tl-filename']/a/span")).getText();
        Thread.sleep(1000);
        Assert.assertEquals(actualText,textParam);

    }
    @Test(priority = 3)
    public void cancelUpdateTest() throws InterruptedException {
                groups.cancelUpdate("mjkihug");
        Thread.sleep(3000);
        driver.navigate().refresh();
        String actualText = driver.findElement(By.xpath("//span[@class='tl-filename']/a/span")).getText();
        Thread.sleep(1000);
        Assert.assertEquals(actualText, textParam);

    }
    @Test(priority = 4)
    public void visibleTest() throws InterruptedException {
                groups.visible();

        String actualText = driver.findElement(By.xpath("(//li[@class='dropdown']/a)[4]")).getText();
        String expectedText = "USER CAN VIEW THAT FILE";
        Assert.assertEquals(actualText, expectedText);

        groups.visible();
         actualText = driver.findElement(By.xpath("(//li[@class='dropdown']/a)[4]")).getText();
         expectedText = "USER CANNOT VIEW THAT FILE";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test(priority = 5)
    public void deleteTest() throws InterruptedException {
        List<WebElement> elementList = driver.findElements(By.xpath("//tbody/tr/td"));
        Assert.assertTrue(elementList.size()>1);
                groups.deleteFile();
                Thread.sleep(2000);
        List<WebElement> elementList2 = driver.findElements(By.xpath("//tbody/tr/td"));
        Assert.assertEquals(elementList2.size(),1);
    }


    }
