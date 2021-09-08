package org.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.base.BasePage;

public class StorePage extends BasePage {

    private By searchField = By.id("woocommerce-product-search-field-0");
    private By searchBtn = By.cssSelector("button[value='Search']");
    private By title = By.xpath("//*[@class='woocommerce-products-header__title page-title']");
    private By viewCartLink = By.xpath("//*[@title='View cart']");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    private StorePage enterTextInSearchField(String txt){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField)).sendKeys(txt);
        return this;
    }

    public StorePage search(String txt){
        enterTextInSearchField(txt).clickSearchBtn();
        return this;
    }

    private StorePage clickSearchBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        return this;
    }

    public String getTitle(){
       return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    private By getAddToCardBtnElement(String prouctID){
         return By.xpath("//*[@data-product_id='"+prouctID+"']");
    }
    public StorePage clickAddToCardBtn(String productID){
        By addToCardBtn = getAddToCardBtnElement(productID);
        wait.until(ExpectedConditions.elementToBeClickable(addToCardBtn)).click();
        return this;
    }

    public CartPage clickViewCart(){
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
        return new CartPage(driver);
    }

}
