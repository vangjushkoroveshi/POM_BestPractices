package org.selenium.base;


import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.constants.DriverType;
import org.selenium.factory.DriverManagerFactory;
import org.selenium.factory.DriverManagerOriginal;
import org.selenium.utils.CookiesUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
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
    public synchronized void setUp(@Optional String browser){
//        if (browser == null) browser = "CHROME";
        browser = System.getProperty("browser", browser);
//        setDriver(new DriverManagerOriginal().initializeDriver(browser));
        setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
    }

    @AfterMethod
    public synchronized void tearDown(){
        getDriver().quit();
    }

    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie> seleniumCookies = new CookiesUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for (Cookie cookie: seleniumCookies){
            getDriver().manage().addCookie(cookie);
        }
    }
}
