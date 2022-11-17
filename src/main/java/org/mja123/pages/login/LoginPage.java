package org.mja123.pages.login;

import io.appium.java_client.android.AndroidDriver;
import org.mja123.pages.BasePage;

public class LoginPage extends BasePage {
    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    public BasePage login() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("bla");
        return this;
    }
}
