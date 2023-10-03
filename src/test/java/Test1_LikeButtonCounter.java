import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test1_LikeButtonCounter {
    WebDriver driver;
    private static String WWW= "https://testcraftsmanship.com/selenium-playground/index.html";

    @BeforeClass
    public void setDriver(){
        System.setProperty("webdriver.chrome.driver", "c:/dev/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(WWW);

    }

    @AfterMethod
    public void closeMethod(){
//        driver.close();
//        driver.quit();
    }

    @Test
    public void scenario1_likeButtonCounterIsIncreasedAfterClicking(){
        HomePage scen1 = new HomePage(driver);
        int actualLikesBeforeClick = scen1.getLikeCount();
        int actualLikesAfterClick = scen1.clickLike().getLikeCount();
        ;
        Assert.assertEquals(actualLikesAfterClick,actualLikesBeforeClick+1, "the number of likes is not correct;" +
                "actual:"+actualLikesAfterClick+ ", expected:"+ (actualLikesBeforeClick+1));




    }

}
