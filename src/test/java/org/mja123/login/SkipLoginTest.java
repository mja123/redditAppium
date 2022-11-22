package org.mja123.login;

import org.mja123.BaseTest;
import org.mja123.pages.WelcomePage;
import org.testng.annotations.Test;

public class SkipLoginTest extends BaseTest {
    @Test(groups = {"regression"})
    public void skipLogin() throws InterruptedException {
        new WelcomePage(driver).skipLogin();

    }
}
