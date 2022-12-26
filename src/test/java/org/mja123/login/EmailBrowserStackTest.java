package org.mja123.login;

import org.json.simple.parser.ParseException;
import org.mja123.BaseTest;
import org.mja123.pages.WelcomePage;
import org.mja123.pages.homePage.HomePage;
import org.testng.Assert;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailBrowserStackTest extends BaseTest {

    @Test(invocationCount = 3)
    public void loginWithCorrectCredentials(ITestContext context) throws IOException, ParseException {
       LOGGER.info("1");

        String device = String.valueOf(Arrays.stream(context.getAllTestMethods())
                .filter(m -> m.getMethodName().equals("loginWithCorrectCredentials"))
                .findFirst()
                .get()
                .getCurrentInvocationCount());
        setUp(device);
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
    @Test(invocationCount = 4)
    public void skipLogin2(ITestContext context) throws IOException, ParseException {
       LOGGER.info("2");

        String device = String.valueOf(Arrays.stream(context.getAllTestMethods())
                .filter(m -> m.getMethodName().equals("skipLogin2"))
                .findFirst()
                .get()
                .getCurrentInvocationCount());
        setUp(device);
        new WelcomePage(driver).skipLogin();
    }

    @Test(invocationCount = 4)
    public void skipLogin3(ITestContext context) throws IOException, ParseException {
        LOGGER.info("3");

        String device = String.valueOf(Arrays.stream(context.getAllTestMethods())
                .filter(m -> m.getMethodName().equals("skipLogin3"))
                .findFirst()
                .get()
                .getCurrentInvocationCount());
        setUp(device);
        new WelcomePage(driver).skipLogin();
    }

    @Test(invocationCount = 4)
    public void skipLogin4(ITestContext context) throws IOException, ParseException {
        LOGGER.info("4");

        String device = String.valueOf(Arrays.stream(context.getAllTestMethods())
                .filter(m -> m.getMethodName().equals("skipLogin4"))
                .findFirst()
                .get()
                .getCurrentInvocationCount());
        setUp(device);
        new WelcomePage(driver).skipLogin();
    }

    @Test(invocationCount = 4)
    public void skipLogin5(ITestContext context) throws IOException, ParseException {
        LOGGER.info("5");
        String device = String.valueOf(Arrays.stream(context.getAllTestMethods())
                .filter(m -> m.getMethodName().equals("skipLogin5"))
                .findFirst()
                .get()
                .getCurrentInvocationCount());
        setUp(device);
        new WelcomePage(driver).skipLogin();
    }
}
