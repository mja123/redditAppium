package org.mja123;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected AndroidDriver driver;
    protected static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("platformVersion", "12.0");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("appPackage", "com.reddit.frontpage");
        capabilities.setCapability("appActivity", ".main.MainActivity");

        LOGGER.info("Instantiating driver");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }
//    @Parameters({"platformName", "automationName", "platformVersion", "deviceName", "app", "packageActivity"})
//    @BeforeMethod(alwaysRun = true)
//    public void setUp(String platformName, String automationName, String platformVersion,
//                      String deviceName, String appActivity, String appPackage) throws MalformedURLException {
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("platformName", platformName);
//        capabilities.setCapability("automationName", automationName);
//        capabilities.setCapability("platformVersion", platformVersion);
//        capabilities.setCapability("deviceName", deviceName);
//        capabilities.setCapability("appPackage", appPackage);
//        capabilities.setCapability("appActivity", appActivity);
//
//        LOGGER.info("Instantiating driver");
//        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
//    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
