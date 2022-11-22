package org.mja123.login;

import org.mja123.BaseTest;
import org.mja123.pages.BasePage;
import org.mja123.pages.ElementNotFound;
import org.mja123.pages.WelcomePage;
import org.mja123.pages.homePage.HomePage;
import org.mja123.pages.signUp.GooglePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class GoogleLoginTest extends BaseTest {

    //I have to provide a non-registered account
    @Test(expectedExceptions = GooglePage.EmailAlreadyExist.class)
    public void loginWithGoogle() throws ElementNotFound, GooglePage.EmailAlreadyExist, IOException {
        LOGGER.info(String.valueOf(MethodHandles.lookup()));
        GooglePage googlePage = (GooglePage) new WelcomePage(driver).login(false).loginWithGoogle("Add another account");
        googlePage.emailInput(searchProperty("EMAIL"));
    }

    @Test(groups = {"regression"})
    public void loginWithFrequentedAccount() throws ElementNotFound, IOException, GooglePage.EmailAlreadyExist {
        LOGGER.info(String.valueOf(MethodHandles.lookup()));
        BasePage googleLogin =  new WelcomePage(driver).login(false).loginWithGoogle("mja123 tests");

        if (googleLogin instanceof GooglePage googlePage) {
            googlePage.emailInput(searchProperty("EMAIL"));
            googlePage.passwordInput(searchProperty("PASSWORD"));
            googlePage.agreeTermsAndConditions();

        } else {
            HomePage homePage = (HomePage) googleLogin;
            Assert.assertNotNull(homePage.getHeader());
        }
    }

    @Test
    public void LoginWithRememberedAccount() {
        LOGGER.info(String.valueOf(MethodHandles.lookup()));
        new WelcomePage(driver).login(true).loginWithFrequentedAccount();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(expectedExceptions = ElementNotFound.class, groups = {"regression"})
    public void loginWithNonexistentOpenAccount() throws ElementNotFound {
        LOGGER.info(String.valueOf(MethodHandles.lookup()));
        new WelcomePage(driver).login(false).loginWithGoogle("Nonexistent account");
    }


}
