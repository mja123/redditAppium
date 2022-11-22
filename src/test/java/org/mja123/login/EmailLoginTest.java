package org.mja123.login;

import org.mja123.BaseTest;
import org.mja123.pages.WelcomePage;
import org.mja123.pages.homePage.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailLoginTest extends BaseTest {

    @Test(groups = {"regression"})
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

    @Test(groups = {"regression"})
    public void loginWithIncorrectCredentials() {
        HomePage homePage = new WelcomePage(driver).login(false).loginWithEmail("asdfasdf", "asdfasdfasd");
    }


}
