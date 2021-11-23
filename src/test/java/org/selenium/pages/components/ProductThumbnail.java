package org.selenium.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.base.BasePage;
import org.selenium.pages.CartPage;
import org.selenium.pages.HomePage;

public class ProductThumbnail extends BasePage {

    private By viewCartLink = By.xpath("//*[@title='View cart']");

    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }

    private By getAddToCardBtnElement(String productName){
        return By.cssSelector("a[aria-label='Add “"+productName+"” to your cart'");
    }

    public ProductThumbnail clickAddToCardBtn(String productName){
        By addToCardBtn = getAddToCardBtnElement(productName);
        wait.until(ExpectedConditions.elementToBeClickable(addToCardBtn)).click();
        return this;
    }

    public CartPage clickViewCart(){
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
        return new CartPage(driver);
    }
}
