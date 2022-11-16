package org.mja123.pages.login;

import io.appium.java_client.android.AndroidDriver;
import org.mja123.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class GoogleLoginPage extends BasePage {
    public GoogleLoginPage(AndroidDriver driver) {
        super(driver);
        searchContext(EContext.WEB);
    }

    private void searchContext(EContext context) {

        if (context.getValue().equals("NATIVE_APP")) {
            driver.context("NATIVE_APP");
        } else {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            driver.context("WEBVIEW_com.google.android.gms.ui");
        }
    }

    public void emailInput(String email) {

        By emailField = By.cssSelector("input[type=\"email\"]");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(emailField));

        driver.findElement(emailField).sendKeys(email);
    }
}

/*
TODO:
 1- Create a test email
 2- Finish the google and email register
 3- Create suites
 4- Start with Jenkins
 */


