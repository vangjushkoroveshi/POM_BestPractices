package org.selenium.tests;

import org.selenium.base.BaseTest;
import org.selenium.objects.Product;
import org.selenium.pages.CartPage;
import org.selenium.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCardTest extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).
                load().
                clickAddToCardBtn(Integer.toString(product.getId())).
                clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }
}
