package tests;

import org.junit.Before;
import org.junit.Test;
import pages.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CalculationTest extends BaseTest {

    private TrainersOverviewPage trainersOverviewPage = new TrainersOverviewPage(wd);
    private BeltsOverviewPage beltsOverviewPage = new BeltsOverviewPage(wd);

    @Before
    public void beforeTest() {
        getPage(beltsOverviewPage.getUrl());
    }

    @Test
    public void testWhetherTheCalculationIsCorrect() {
        try {
            List<String> productDescriptions = new ArrayList<>();
            BeltsPage beltsPage = beltsOverviewPage.selectProduct();
            BigDecimal beltsPrice = parsePrice(beltsPage.selectSize()
                    .clickOnAddToBag()
                    .getProductPrice());
            productDescriptions.add(beltsPage.getProductName());

            getPage(trainersOverviewPage.getUrl());
            TrainersPage trainersPage = trainersOverviewPage.selectProduct();
            BigDecimal twoTrainersPrice = parsePrice(trainersPage.selectSize()
                    .clickOnAddToBag()
                    .getProductPrice()).multiply(new BigDecimal(2));
            productDescriptions.add(trainersPage.getProductName());

            BagPage bagPage = beltsPage.goToBag();
            bagPage.removeExtraProducts(productDescriptions)
                    .addProduct(productDescriptions.get(1))
                    .clickUpdateBagSign();

            BigDecimal expectedTotalPrice = beltsPrice.add(twoTrainersPrice);
            BigDecimal actualTotalPrice = parsePrice(bagPage.getTotalPrice());
            assertEquals(expectedTotalPrice, actualTotalPrice);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private BigDecimal parsePrice(String price) throws ParseException {
        BigDecimal amount = new BigDecimal(price.replace("£", ""));
        return amount.setScale(2, RoundingMode.HALF_EVEN);
    }
}
