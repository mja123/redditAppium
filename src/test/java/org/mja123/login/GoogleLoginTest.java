package org.mja123.login;

import org.mja123.BaseTest;
import org.mja123.pages.ElementNotFound;
import org.mja123.pages.WelcomePage;
import org.mja123.pages.homePage.HomePage;
import org.mja123.pages.signUp.GooglePage;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

public class GoogleLoginTest extends BaseTest {

    @Test
    public void loginWithCorrectCredentials() {
        LOGGER.info(String.valueOf(MethodHandles.lookup()));
        new WelcomePage(driver).login(false).loginWithEmail("mja123test", "reddit123");

    }

    //I have to provide a non-registered account
    @Test(expectedExceptions = GooglePage.EmailAlreadyExist.class)
    public void loginWithGoogle() throws ElementNotFound, GooglePage.EmailAlreadyExist {
        LOGGER.info(String.valueOf(MethodHandles.lookup()));
        GooglePage googlePage = (GooglePage) new WelcomePage(driver).login(false).loginWithGoogle("Add another account");
        googlePage.emailInput("mja123reddittest@gmail.com");
    }
    @Test
    public void loginWithFrequentedAccount() throws ElementNotFound {
        LOGGER.info(String.valueOf(MethodHandles.lookup()));
        HomePage homePage = (HomePage) new WelcomePage(driver).login(false).loginWithGoogle("mja123 tests");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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

    @Test(expectedExceptions = ElementNotFound.class)
    public void loginWithNonexistentOpenAccount() throws ElementNotFound {
        LOGGER.info(String.valueOf(MethodHandles.lookup()));
        new WelcomePage(driver).login(false).loginWithGoogle("Nonexistent account");
    }
}
