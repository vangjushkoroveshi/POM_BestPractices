package org.selenium.tests;

import org.selenium.base.BaseTest;
import org.selenium.dataProviders.MyDataProvider;
import org.selenium.objects.Product;
import org.selenium.pages.CartPage;
import org.selenium.pages.HomePage;
import org.selenium.pages.StorePage;
import org.selenium.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCardTest extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).load().
                getProductThumbnail().
                clickAddToCardBtn(product.getName()).
                clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }

    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = MyDataProvider.class)
    public void addToCartFeatureProducts(Product product){

        CartPage cartPage = new HomePage(getDriver()).load().
                getProductThumbnail().clickAddToCardBtn(product.getName()).
                clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }
}
