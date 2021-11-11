package org.selenium.base;


import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.factory.DriverManager;
import org.selenium.utils.CookiesUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.List;

public class BaseTest {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    protected WebDriver getDriver() {
        return this.driver.get();
    }


    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser){
        browser = System.getProperty("browser", browser);
        setDriver(new DriverManager().initializeDriver(browser));
    }

    @AfterMethod
    public void tearDown(){
        getDriver().quit();
    }

    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie> seleniumCookies = new CookiesUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for (Cookie cookie: seleniumCookies){
            getDriver().manage().addCookie(cookie);
        }
    }
}
