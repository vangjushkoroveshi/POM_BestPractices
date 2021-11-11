package org.selenium.tests;

import org.selenium.api.actions.CartApi;
import org.selenium.api.actions.SignUpApi;
import org.selenium.base.BaseTest;
import org.selenium.objects.Product;
import org.selenium.objects.User;
import org.selenium.pages.CheckoutPage;
import org.selenium.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void loginDuringCheckout() throws IOException, InterruptedException {
        String username = "demouser"+ new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword("demopwd").
                setEmail(username+"@gmail.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCard(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        Thread.sleep(5000);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load();
        Thread.sleep(5000);

        checkoutPage.clickHereToLoginLink().login(user);

        Thread.sleep(5000);
        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));
    }
}
