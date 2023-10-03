import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class SeleniumBaseTest {
    protected WebDriver driver;
    private static String WWW= "https://testcraftsmanship.com/selenium-playground/index.html";

    @BeforeMethod
    public void setDriver(){
        System.setProperty("webdriver.chrome.driver", "c:/dev/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(WWW);

    }

    @AfterMethod
    public void closeMethod(){
        driver.quit();
    }
}
