package org.mja123.pages.login;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.mja123.pages.BasePage;
import org.mja123.pages.ElementNotFound;
import org.mja123.pages.homePage.HomePage;
import org.mja123.pages.signUp.GooglePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class LoginPage extends BasePage {

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    public BasePage loginWithGoogle(String account) throws ElementNotFound {

        By googleButton = AppiumBy.id("google_sso_button");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(googleButton));


        driver.findElement(googleButton).click();

        return loginWithFrequentGoogleAccount(account);

    }

    public HomePage loginWithEmail(String email, String password) {

        By credentialsLocator = AppiumBy.className("android.widget.EditText");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(credentialsLocator));

        List<WebElement> credentials = driver.findElements(credentialsLocator);

        credentials.get(0).sendKeys(email);
        credentials.get(1).sendKeys(password);

        driver.findElement(AppiumBy.id("confirm")).findElement(AppiumBy.className("android.widget.Button")).click();

        return new HomePage(driver);
    }

    public HomePage loginWithFrequentedAccount() {
        By parentContainer = AppiumBy.id("account_picker_accounts");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(parentContainer));

        driver.findElement(parentContainer).findElement(AppiumBy.className("android.view.ViewGroup")).click();

        return new HomePage(driver);
    }

    private BasePage loginWithFrequentGoogleAccount(String account) throws ElementNotFound {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.google.android.gms:id/account_display_name")));


//            By accountList = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.google.android.gms:id/container\")");

//            new WebDriverWait(driver, Duration.ofSeconds(2))
//                    .until(ExpectedConditions.elementToBeClickable(accountList));


            //List<WebElement> accountOptions = driver.findElements(accountList);
            List<WebElement> accountOptions = driver.findElements(AppiumBy
                    .androidUIAutomator("new UiSelector().resourceId(\"com.google.android.gms:id/container\")"));

            if (account.equals("Add another account")) {

                LOGGER.info("Adding new google account");
                accountOptions.get(accountOptions.size() - 1).click();
                return new GooglePage(driver);
            }

            Optional<WebElement> targetAccount = accountOptions.stream()
                    .filter(a -> a.findElement(AppiumBy.id("com.google.android.gms:id/account_display_name"))
                            .getText().equals(account))
                    .findFirst();

            if (targetAccount.isPresent()) {
                LOGGER.info("Selecting " + account + " account");
                targetAccount.get().click();
                return new HomePage(driver);
            }

            throw new ElementNotFound("Account not found!");

        } catch (TimeoutException e) {
            LOGGER.info("There isn't any registered google account");
            return new GooglePage(driver);
        } catch (NoSuchElementException e) {
            throw new ElementNotFound("Account not found!");
        }
    }


}
