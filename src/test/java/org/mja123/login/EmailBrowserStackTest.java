package org.mja123.login;

import org.mja123.BaseTest;
import org.mja123.pages.WelcomePage;
import org.mja123.pages.homePage.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class EmailBrowserStackTest extends BaseTest {

    @Test
    public void loginWithCorrectCredentials() throws IOException {
        String username = searchProperty("USERNAME");
        String password = searchProperty("PASSWORD");

        HomePage homePage = new WelcomePage(driver).login(false).loginWithEmail(username, password);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertNotNull(homePage.getHeader());
    }

}
