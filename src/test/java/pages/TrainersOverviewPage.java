package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class TrainersOverviewPage extends NavigationPage {

    @FindBy(css = "#productlistcontainer a.ProductImageList")
    private List<WebElement> products;

    public TrainersOverviewPage(WebDriver wd) {
        super(wd);
    }

    public String getUrl() {
        return BASE_URL + "/ladies/ladies-trainers";
    }

    public TrainersPage selectProduct() {
        int size = products.size();
        products.get(new Random().nextInt(size)).click();
        return new TrainersPage(wd);
    }
}
