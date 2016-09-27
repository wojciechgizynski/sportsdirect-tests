package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static utils.OsUtils.killProcess;

public class WebDriverManager {

    private static Logger log = LoggerFactory.getLogger(WebDriverManager.class);

    private static WebDriver getWebDriver() {
        killProcess("chromedriver.exe");
        DesiredCapabilities chromeCaps = DesiredCapabilities.chrome();
        chromeCaps.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.BOTTOM);
        return new ChromeDriver(chromeCaps);
    }

    public static WebDriver getDriver() {
        log.info("Going to start Chrome browser");
        EventFiringWebDriver wd = new EventFiringWebDriver(getWebDriver());
        wd.register(new ModalDialogListener());

        WebDriver.Timeouts timeouts = wd.manage().timeouts();

        timeouts.pageLoadTimeout(30, TimeUnit.SECONDS);
        timeouts.setScriptTimeout(30, TimeUnit.SECONDS);
        timeouts.implicitlyWait(10, TimeUnit.SECONDS);
        wd.manage().window().maximize();
        return wd;
    }
}
