package org.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.base.BasePage;
import org.selenium.pages.components.ProductThumbnail;

public class StorePage extends BasePage {



    private By searchField = By.id("woocommerce-product-search-field-0");
    private By searchBtn = By.cssSelector("button[value='Search']");
    private By title = By.xpath("//*[@class='woocommerce-products-header__title page-title']");

    private ProductThumbnail productThumbnail;

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail =  new ProductThumbnail(driver);
    }

    private StorePage enterTextInSearchField(String txt){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField)).sendKeys(txt);
        return this;
    }

    public StorePage load(){
        load("/store");
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
}
