package org.mja123.login;

import org.mja123.BaseTest;
import org.mja123.pages.ElementNotFound;
import org.mja123.pages.login.ELoginOptions;
import org.mja123.pages.login.EmailLoginPage;
import org.mja123.pages.login.GoogleLoginPage;
import org.mja123.pages.login.LoginPage;
import org.testng.annotations.Test;

public class GoogleLoginTest extends BaseTest {

    @Test
    public void enterWithValidCredentials() throws ElementNotFound {
        GoogleLoginPage loginPage = (GoogleLoginPage) new LoginPage(driver).loginOption(ELoginOptions.GOOGLE);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
