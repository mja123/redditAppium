package org.mja123.login;

import com.password4j.AlgorithmFinder;
import com.password4j.Argon2Function;
import org.mja123.BaseTest;
import org.mja123.pages.WelcomePage;
import org.mja123.pages.homePage.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Optional;
import java.util.Properties;

public class EmailLoginTest extends BaseTest {

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

    private String searchProperty(String property) throws IOException {
        InputStream input = new FileInputStream(System.getProperty("user.dir").concat( "/src/test/resources/credentials.properties"));

        Properties properties = new Properties();
        properties.load(input);
        String target = properties.getProperty(property);

        input.close();

        return target;
    }
}
