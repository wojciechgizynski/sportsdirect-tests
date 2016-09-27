package utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;

public class ModalDialogListener extends AbstractWebDriverEventListener {

    public void beforeClickOn(WebElement element, WebDriver driver) {
        await().atMost(1, TimeUnit.MINUTES).pollInterval(3, TimeUnit.SECONDS)
                .until(() -> {
                    try {
                        element.isDisplayed();

                    } catch (NoSuchElementException | StaleElementReferenceException e) {
                        Actions builder = new Actions(driver);
                        builder.moveToElement(element, 100, 100).click().build().perform();
                    }
                });
    }

    private void refreshToCloseModals(WebDriver driver) {
        driver.navigate().refresh();
    }

}
