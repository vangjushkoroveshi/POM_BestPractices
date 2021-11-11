package org.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.base.BasePage;

public class HomePage extends BasePage {
    private By storeMenuLink = By.xpath("//li[@id='menu-item-1227']/a");
    private By viewCartLink = By.xpath("//*[@title='View cart']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage load(){
        load("/");
        return this;
    }

    public StorePage navigateToStoreUsingMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(storeMenuLink)).click();
        return new StorePage(driver);
    }

    private By getAddToCardBtnElement(String productName){
        return By.cssSelector("a[aria-label='Add “"+productName+"” to your cart'");
    }

    public HomePage clickAddToCardBtn(String productName){
        By addToCardBtn = getAddToCardBtnElement(productName);
        wait.until(ExpectedConditions.elementToBeClickable(addToCardBtn)).click();
        return this;
    }

    public CartPage clickViewCart(){
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
        return new CartPage(driver);
    }
}
