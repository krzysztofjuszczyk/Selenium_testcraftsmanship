import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[id=like-it]")
    private WebElement likeBtn;

    @FindBy(id = "number-of-likes")
    private WebElement getLikeBtnCount;

    @FindBy(css = "input[id=search-names-input]")
    private WebElement searchNamesTxt;

    @FindBy(className = "filtered-table")
    private WebElement filteredTable;

    @FindBy(css = "#country-table>tbody>tr")
    private List<WebElement> namesList;

    @FindBy(css = "#country-table>tbody>tr[style='display: none;']")
    private List<WebElement> hiddenNamesList;

    @FindBy(id = "page-heading-timer")
    private WebElement timer;

    public int getLikeCount() {
        return Integer.parseInt(getLikeBtnCount.getText());
    }

    public HomePage clickLike() {
        likeBtn.click();
//        getLikeBtnCount.wait(1000);
        synchronized (getLikeBtnCount) {
            try {
                getLikeBtnCount.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public HomePage typeSearch(String text) {
//        searchNamesTxt.clear();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search-names-input")));
        searchNamesTxt.sendKeys(text + Keys.ENTER);
        synchronized (searchNamesTxt) {
            try {
                searchNamesTxt.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return this;
    }

    public HomePage assertListOfNamesContains(String name) {
        Assert.assertFalse(namesList.isEmpty());

        boolean containsName = false;
        for (int i = 0; i < namesList.size(); i++) {
            String nameInList = namesList.get(i).getText();
            System.out.println(nameInList);
            if (nameInList.contains(name)) containsName = true;
        }
        Assert.assertTrue(containsName);
        return this;
    }

    public HomePage assertListOfNamesIsOfSize(int size) {
        Assert.assertFalse(namesList.isEmpty());
        int all = 11;

        Assert.assertTrue(all - hiddenNamesList.size() == size, "hidden elements count:" + hiddenNamesList.size() + "; expected: " + size);

        return this;
    }

    public HomePage assertTimerDateIsNowPlusMinusXSeconds(int x) {
        LocalDateTime now = LocalDateTime.now();
        String localDate = (now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth());
        LocalDateTime xSecondsLater = now.plusSeconds(x);
        LocalDateTime xSecondsEarlier = now.minusSeconds(x);

        String timerTxt = timer.getText();
        while (timerTxt.equals("")) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("page-heading-timer")));
            timerTxt = timer.getText();
        }

        String timestampAsString = (localDate + " " + timerTxt);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d HH:mm:ss");
        LocalDateTime localDateTimeFromTimer = LocalDateTime.parse(timestampAsString, formatter);
        System.out.println(localDateTimeFromTimer);

        long diffPlus = ChronoUnit.SECONDS.between(localDateTimeFromTimer, xSecondsLater);
        long diffMinus = ChronoUnit.SECONDS.between(localDateTimeFromTimer, xSecondsEarlier);
        System.out.println("fromTimer: " + localDateTimeFromTimer);
        System.out.println("later: " + xSecondsLater);
        System.out.println("diff Plus: " + diffPlus);
        System.out.println("earlier: " + xSecondsEarlier);
        System.out.println("diffMinus: " + diffMinus);
        Assert.assertTrue(diffPlus <= 5 && diffMinus >= -5);

        return this;
    }
}


