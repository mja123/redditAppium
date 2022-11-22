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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class BaseTest {
    protected AndroidDriver driver;
    protected static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @Parameters({"platformName", "automationName", "platformVersion", "deviceName", "packageActivity", "appPackage"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String platformName, String automationName, String platformVersion,
                      String deviceName, String appActivity, String appPackage) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("automationName", automationName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);

        LOGGER.info("Instantiating driver");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    protected String searchProperty(String property) throws IOException {
        InputStream input = new FileInputStream(System.getProperty("user.dir").concat("/src/test/resources/credentials.properties"));

        Properties properties = new Properties();
        properties.load(input);
        String target = properties.getProperty(property);

        input.close();

        return target;
    }
}
