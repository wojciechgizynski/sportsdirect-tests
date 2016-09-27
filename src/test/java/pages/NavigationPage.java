package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static utils.WebUtils.isElementDisplayed;

public class NavigationPage extends BasePage {

    @FindBy(css = "#aBagLink")
    private WebElement bagSign;

    @FindBy(id = "CloseNewsLetterModal")
    private WebElement newsletterCloseSign;

    public NavigationPage(WebDriver wd) {
        super(wd);
    }

    public BagPage goToBag() {
        log.debug("Opening Bag page");
        bagSign.click();
        return new BagPage(wd);
    }

    public BagPage closeNewsLetterModal() {
        await().atMost(2, TimeUnit.MINUTES).pollInterval(3, TimeUnit.SECONDS)
                .until(() -> isElementDisplayed(newsletterCloseSign));
        newsletterCloseSign.click();
        return new BagPage(wd);
    }
}
