package org.mja123.login;

import org.mja123.BaseTest;
import org.mja123.pages.ElementNotFound;
import org.mja123.pages.login.LoginPage;
import org.mja123.pages.signUp.ESignUpOptions;
import org.mja123.pages.signUp.GooglePage;
import org.mja123.pages.signUp.SignUpPage;
import org.testng.annotations.Test;

public class GoogleLoginTest extends BaseTest {

    @Test
    public void tryingWits() {
        new LoginPage(driver).login();
    }
}
