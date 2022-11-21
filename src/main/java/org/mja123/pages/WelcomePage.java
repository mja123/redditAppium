package org.mja123.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.mja123.pages.homePage.HomePage;
import org.mja123.pages.login.LoginPage;
import org.mja123.pages.signUp.ESignUpOptions;
import org.mja123.pages.signUp.EmailSignUpPage;
import org.mja123.pages.signUp.GooglePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WelcomePage extends BasePage {

    private WebElement loginButton;
    public WelcomePage(AndroidDriver driver) {
        super(driver);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        loginButton = driver.findElement(AppiumBy.id("login_button"));
    }

    public BasePage signUpOptions(ESignUpOptions option) throws ElementNotFound {

        LOGGER.info("Signup options");

        WebElement loginOption = factorySelector(option, AppiumBy.className("android.widget.Button"));

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginOption));

        loginOption.click();

        return (switch (option) {
            case EMAIL -> new EmailSignUpPage(driver);
            case GOOGLE -> new GooglePage(driver);
            default -> new HomePage(driver);
        });
    }

    public LoginPage login(boolean useOpenSession) {

        if (!useOpenSession) {
            try {
                LOGGER.info("Searching google open session");
                By recentEmail = AppiumBy
                        .androidUIAutomator("new UiSelector().resourceId(\"com.google.android.gms:id/cancel\"");

                new WebDriverWait(driver, Duration.ofSeconds(1))
                        .until(ExpectedConditions.elementToBeClickable(recentEmail));

                driver.findElement(recentEmail).click();
            } catch(TimeoutException e) {
                LOGGER.info("Recent email doesn't found");
            }


            try {
                loginButton.click();
                LOGGER.info("Searching email open session");

                new WebDriverWait(driver, Duration.ofSeconds(1))
                        .until(ExpectedConditions.elementToBeClickable(AppiumBy.id("account_remove")));
                logOutRecentAccounts();

            } catch(TimeoutException e) {
                LOGGER.info("There isn't an open session");
                return new LoginPage(driver);
            }
        }

        loginButton.click();
        return new LoginPage(driver);

    }

    public HomePage skipLogin() {

        driver.findElement(AppiumBy.id("skip_button")).click();
        return new HomePage(driver);
    }

    private void logOutRecentAccounts() {
        By logOutAccount = AppiumBy.id("account_remove");

        LOGGER.info("Closing open session");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(logOutAccount));

        driver.findElement(logOutAccount).click();

        By confirmLogOut = AppiumBy.id("confirm_remove_account_logout");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(confirmLogOut));

        driver.findElement(confirmLogOut).click();
    }
}
