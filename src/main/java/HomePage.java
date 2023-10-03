import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy (css = "button[id=like-it]")
    private WebElement likeBtn;

    @FindBy (id = "number-of-likes")
    private WebElement getLikeBtnCount;

    public int getLikeCount(){
        return Integer.parseInt(getLikeBtnCount.getText());
    }

    public HomePage clickLike(){
        likeBtn.click();
        return this;
    }

}


