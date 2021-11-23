package org.selenium.base;


import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.constants.DriverType;
import org.selenium.factory.DriverManagerFactory;
import org.selenium.factory.DriverManagerOriginal;
import org.selenium.factory.abstractFactory.DriverManagerAbstract;
import org.selenium.factory.abstractFactory.DriverManagerFactoryAbstract;
import org.selenium.utils.CookiesUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private ThreadLocal<DriverManagerAbstract> driverManager = new ThreadLocal<>();

    protected void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    protected void setDriverManager(DriverManagerAbstract driverManager) {
        this.driverManager.set(driverManager);
    }

    protected DriverManagerAbstract getDriverManager() {
        return this.driverManager.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public synchronized void setUp(@Optional String browser){
//        if (browser == null) browser = "CHROME";
        browser = System.getProperty("browser", browser);
//        setDriver(new DriverManagerOriginal().initializeDriver(browser));
//        setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
        setDriverManager(DriverManagerFactoryAbstract.getManager(DriverType.valueOf(browser)));
        setDriver(getDriverManager().getDriver());
    }

    @AfterMethod
    @Parameters("browser")
    public synchronized void tearDown(@Optional String browser, ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE){
            File destFile = new File(System.getProperty("user.dir")+"\\screenshots"+ File.separator+ browser+ File.separator + result.getTestClass().getRealClass().getSimpleName()
                    + "_" + result.getMethod().getMethodName()+ ".png");
            takeScreenshot(destFile);
        }

        getDriver().quit();
    }

    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie> seleniumCookies = new CookiesUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for (Cookie cookie: seleniumCookies){
            getDriver().manage().addCookie(cookie);
        }
    }

    private void takeScreenshot(File destFile) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile,destFile);
    }
}
