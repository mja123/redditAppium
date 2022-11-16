package org.mja123.pages.login;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.mja123.pages.BasePage;
import org.mja123.pages.ElementNotFound;
import org.mja123.pages.homePage.HomePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage  extends BasePage {

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    public BasePage loginOption(ELoginOptions option) throws ElementNotFound {
        WebElement loginOption = factorySelector(option, AppiumBy.className("android.widget.Button"));
        System.out.println(loginOption.isEnabled());
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginOption));

        loginOption.click();

        return (switch (option) {
            case EMAIL -> new EmailLoginPage(driver);
            case GOOGLE -> new GoogleLoginPage(driver);
            default -> new HomePage(driver);
        });
    }
}
