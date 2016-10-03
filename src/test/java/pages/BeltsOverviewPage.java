package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WebDriverManager;

import java.util.List;
import java.util.Random;

public class BeltsOverviewPage extends NavigationPage {

    @FindBy(css = "#productlistcontainer a.ProductImageList")
    private List<WebElement> products;

    public BeltsOverviewPage(WebDriver wd) {
        super(wd);
    }

    public String getUrl() {
        return BASE_URL + "accessories/belts";
    }

    public BeltsPage selectProduct() {
        int size = products.size();
        WebElement product = products.get(new Random().nextInt(size));
        //workaround to avoid multiple pop-ups closing
        ((JavascriptExecutor) WebDriverManager.getDriver()).executeScript("arguments[0].click();", product);
        return new BeltsPage(wd);
    }
}
