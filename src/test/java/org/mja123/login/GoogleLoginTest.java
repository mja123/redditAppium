package org.mja123.login;

import org.mja123.BaseTest;
import org.mja123.pages.ElementNotFound;
import org.mja123.pages.WelcomePage;
import org.mja123.pages.homePage.HomePage;
import org.mja123.pages.signUp.GooglePage;
import org.testng.annotations.Test;

public class GoogleLoginTest extends BaseTest {

    @Test
    public void loginWithCorrectCredentials() {
        new WelcomePage(driver).login(false).loginWithEmail("mja123test", "reddit123");

    }

    //I have to provide an non registered account
    @Test
    public void loginWithGoogle() throws ElementNotFound {
        GooglePage googlePage = (GooglePage) new WelcomePage(driver).login(false).loginWithGoogle("Add another account");
        googlePage.emailInput("mja123reddittest@gmail.com");
        googlePage.passwordInput("reddit123");
        googlePage.agreeTermsAndConditions();
    }
    @Test
    public void loginWithFrequentedAccount() throws ElementNotFound {
        HomePage homePage = (HomePage) new WelcomePage(driver).login(false).loginWithGoogle("mja123 tests");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void LoginWithRememberedAccount() {
        new WelcomePage(driver).login(true).loginWithFrequentedAccount();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
