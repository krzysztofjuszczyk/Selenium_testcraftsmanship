import org.testng.Assert;
import org.testng.annotations.Test;

import static java.time.Duration.ofSeconds;

public class Tests extends SeleniumBaseTest{

    @Test
    public void scenario1_likeButtonCounterIsIncreasedAfterClicking() throws InterruptedException {
        HomePage scen1 = new HomePage(driver);
        int actualLikesBeforeClick = scen1.getLikeCount();
        scen1.clickLike();
        int actualLikesAfterClick = scen1.getLikeCount();
        ;
        Assert.assertEquals(actualLikesAfterClick,actualLikesBeforeClick+1, "the number of likes is not correct;" +
                "actual:"+actualLikesAfterClick+ ", expected:"+ (actualLikesBeforeClick+1));
    }

    @Test
    public void scenario2_namesTableContainsSearchedName(){
        String name = "Gertrud";
        new HomePage(driver)
                .typeSearch(name)
                .assertListOfNamesIsOfSize(1)
                .assertListOfNamesContains(name);

    }
    @Test
    public void scenario3_validateTimeIsNow(){

        new HomePage(driver)
                .assertTimerDateIsNowPlusMinusXSeconds(5)
        ;

    }
}
