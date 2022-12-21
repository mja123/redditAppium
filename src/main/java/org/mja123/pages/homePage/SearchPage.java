package org.mja123.pages.homePage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.mja123.pages.BasePage;
import org.mja123.pages.login.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage extends BasePage {
    private final WebElement bar;
    private List<WebElement> titles;

    public SearchPage(RemoteWebDriver driver) {
        super(driver);
        this.bar = driver.findElement(AppiumBy.id("feed_control_search_icon"));
        tendenciesTitle();
    }

    private void tendenciesTitle() {
        driver.findElements(AppiumBy.id("title"));
    }

    private List<WebElement> searchPostResults() {
        By posts = AppiumBy.id("subreddit_header");

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(posts));

        return driver.findElements(posts);
    }


}
