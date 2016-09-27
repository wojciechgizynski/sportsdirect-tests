package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {
    protected Logger log = LoggerFactory.getLogger(getClass());
    protected WebDriver wd;
    protected static final String BASE_URL = "http://www.sportsdirect.com/";

    public BasePage(WebDriver wd) {
        this.wd = wd;
        PageFactory.initElements(wd, this);
    }
}
