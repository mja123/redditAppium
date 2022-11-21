package org.mja123.pages.homePage;

import io.appium.java_client.android.AndroidDriver;
import org.mja123.pages.BasePage;
import org.mja123.pages.components.HeaderPage;

public class HomePage extends BasePage {
    private HeaderPage header;
    public HomePage(AndroidDriver driver) {
        super(driver);
        header = new HeaderPage(driver);
    }

    public HeaderPage getHeader() {
        return header;
    }
}
