package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BagPage extends BasePage {

    @FindBy(css = ".productTitle")
    private List<WebElement> productDescriptions;

    @FindBy(css = ".prodelete")
    private List<WebElement> removeSign;

    @FindBy(id = "TotalValue")
    private WebElement totalValue;

    @FindBy(css = ".BasketQuantBut.s-basket-plus-button")
    private List<WebElement> addProductSigns;

    @FindBy(css = ".UpdateSurround .NewUpdateQuant")
    private WebElement updateBagSign;

    public BagPage(WebDriver wd) {
        super(wd);
    }

    private List<WebElement> findExtraProducts(List<String> descriptions) {
        return productDescriptions.stream().filter(p -> !descriptions.contains(p.getText())).collect(Collectors.toList());
    }

    private Optional<WebElement> findProduct(String description) {
        return productDescriptions.stream().filter(p -> p.getText().equalsIgnoreCase(description)).findFirst();
    }

    public BagPage addProduct(String description) {
        log.debug(String.format("Adding product: %s", description));
        findProduct(description).get()
                .findElement(By.xpath("ancestor::tr[1]//a[@class='BasketQuantBut s-basket-plus-button']")).click();
        return this;
    }

    public BagPage removeExtraProducts(List<String> descriptions) {
        log.debug("Removing extra products");
        findExtraProducts(descriptions).stream()
                .map(p -> p.findElement(By.xpath("ancestor::div[1]//a[@class='prodelete']"))).forEach(WebElement::click);
        return this;
    }

    public String getTotalPrice() {
        log.debug("Getting total value");
        return totalValue.getText();
    }

    public BagPage clickUpdateBagSign() {
        log.debug("Clicking on update bag sign");
        updateBagSign.click();
        return this;
    }
}
