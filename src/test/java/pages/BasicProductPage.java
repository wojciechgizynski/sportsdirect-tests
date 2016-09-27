package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public abstract class BasicProductPage<T extends BasicProductPage<T>> extends NavigationPage {

    @FindBy(css = ".pdpPrice span")
    private WebElement productPrice;

    @FindBy(css = "a.addToBag")
    private WebElement addToBagSign;

    @FindBy(id = "ProductName")
    private WebElement productName;

    @FindBy(xpath = "//li[@class='tooltip sizeButtonli ']")
    private List<WebElement> availableSizes;

    public BasicProductPage(WebDriver wd) {
        super(wd);
    }

    public String getProductPrice() {
        log.debug("Getting product price");
        return productPrice.getText();
    }

    public T clickOnAddToBag() {
        log.debug("Clicking on Add to bag sign");
        addToBagSign.click();
        return (T) this;
    }

    public String getProductName() {
        log.debug("Getting product name");
        return productName.getText();
    }

    public T selectSize() {
        log.debug("Selecting trainers' size");
        int availableSizesSize = availableSizes.size();
        if (availableSizesSize > 0) {
            availableSizes.get(new Random().nextInt(availableSizesSize)).click();
        }
        return (T) this;
    }
}
