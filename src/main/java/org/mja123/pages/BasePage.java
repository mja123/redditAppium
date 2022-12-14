package org.mja123.pages;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

public abstract class BasePage {
    protected static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    protected RemoteWebDriver driver;

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
    }


    protected WebElement factorySelector(IEnums option, By locator) throws ElementNotFound {

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));

        List<WebElement> elementOptions  = driver.findElements(locator);

        Optional<WebElement> target = elementOptions.stream()
                .filter(o -> option.getValue().equals(o.getText()))
                .findFirst();

        if (target.isPresent()) {
            return target.get();
        }

        throw new ElementNotFound("Element not found");
    }

}
