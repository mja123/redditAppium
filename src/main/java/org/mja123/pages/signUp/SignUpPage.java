package org.mja123.pages.signUp;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.mja123.pages.BasePage;
import org.mja123.pages.ElementNotFound;
import org.mja123.pages.homePage.HomePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage extends BasePage {

    public SignUpPage(AndroidDriver driver) {
        super(driver);
    }

    public BasePage loginOption(ESignUpOptions option) throws ElementNotFound {
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
}
