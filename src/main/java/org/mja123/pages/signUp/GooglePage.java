package org.mja123.pages.signUp;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.mja123.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GooglePage extends BasePage {
    public GooglePage(AndroidDriver driver) {
        super(driver);
    }


    public void emailInput(String email) {

        By emailField = AppiumBy.className("android.widget.EditText");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(emailField));

        driver.findElement(emailField).sendKeys(email);

        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Next\")")).click();
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
}

/*
TODO:
 1- Create a test email
 2- Finish the google and email register
 3- Create suites
 4- Start with Jenkins
 */


