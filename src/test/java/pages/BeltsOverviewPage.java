package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        products.get(new Random().nextInt(size)).click();
        return new BeltsPage(wd);
    }
}
