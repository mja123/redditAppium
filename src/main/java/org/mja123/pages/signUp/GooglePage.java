package org.mja123.pages.signUp;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.mja123.pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GooglePage extends BasePage {
    public GooglePage(AndroidDriver driver) {
        super(driver);
    }


    public void emailInput(String email) throws EmailAlreadyExist {

        By emailField = AppiumBy.className("android.widget.EditText");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(emailField));

        driver.findElement(emailField).sendKeys(email);

        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Next\")")).click();

        try {
            driver.findElement(AppiumBy
                    .androidUIAutomator("new UiSelector().textContains(\"This account already exists on your device\")"));

            throw new EmailAlreadyExist("This account is already registered");

        } catch(NoSuchElementException e) {
            LOGGER.info("Email not registered");
        }
    }
    public void passwordInput(String password) {

        By passwordField = AppiumBy.className("android.widget.EditText");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(passwordField));

        driver.findElement(passwordField).sendKeys(password);

        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"passwordNext\")")).click();

    }

    public void agreeTermsAndConditions() {

        By agreeButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"I agree\")");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(agreeButton));

        driver.findElement(agreeButton).click();

    }
    public static class EmailAlreadyExist extends Exception {
        public EmailAlreadyExist(String message) {
            super(message);
        }
    }
}