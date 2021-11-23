package org.selenium.tests;

import org.selenium.base.BaseTest;
import org.selenium.objects.BillingAddress;
import org.selenium.objects.Product;
import org.selenium.objects.User;
import org.selenium.pages.CartPage;
import org.selenium.pages.CheckoutPage;
import org.selenium.pages.HomePage;
import org.selenium.pages.StorePage;
import org.selenium.utils.ConfigLoader;
import org.selenium.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GuestCheckoutTest extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {

        /**
         * Get data from json file using Jackson
         */
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(getDriver()).
                load().getMyHeader().
                navigateToStoreUsingMenu().
                search(searchFor);
        Assert.assertEquals(storePage.getTitle(), "Search results: “"+searchFor+"”");

        storePage.getProductThumbnail().clickAddToCardBtn(product.getName());

        CartPage  cartPage = storePage.getProductThumbnail().clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage.
                checkout().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User(ConfigLoader.getInstance().getUsername(), ConfigLoader.getInstance().getPassword());

        StorePage storePage = new HomePage(getDriver()).
                load().getMyHeader().
                navigateToStoreUsingMenu().
                search(searchFor);
        Assert.assertEquals(storePage.getTitle(), "Search results: “"+searchFor+"”");

        storePage.getProductThumbnail().clickAddToCardBtn(product.getName());
        CartPage  cartPage = storePage.getProductThumbnail().clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.clickHereToLoginLink();
        checkoutPage.
                login(user).
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();
        Thread.sleep(5000);
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }
}
