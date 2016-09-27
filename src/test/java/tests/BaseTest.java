package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static utils.WebDriverManager.getDriver;

public class BaseTest {
    protected Logger log = LoggerFactory.getLogger(getClass());
    protected static WebDriver wd;

    @BeforeClass
    public static void beforeClass() {
        wd = getDriver();
    }

    @AfterClass
    public static void afterClass() {
        Optional.ofNullable(wd).ifPresent(WebDriver::quit);
    }

    public static void getPage(String url) {
        wd.get(url);
    }
}
