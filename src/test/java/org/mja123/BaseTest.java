package org.mja123;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.appium.java_client.android.AndroidDriver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {
    protected RemoteWebDriver driver;
    private DesiredCapabilities capabilities;
    protected static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


//    @Parameters({"platformName", "automationName", "platformVersion", "deviceName", "packageActivity", "appPackage"})
////    @BeforeMethod(alwaysRun = true)
//    public void setUp(String platformName, String automationName, String platformVersion,
//                      String deviceName, String appActivity, String appPackage) throws MalformedURLException {
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("platformName", platformName);
//        capabilities.setCapability("automationName", automationName);
//        capabilities.setCapability("platformVersion", platformVersion);
//        capabilities.setCapability("deviceName", deviceName);
//        capabilities.setCapability("appPackage", appPackage);
//        capabilities.setCapability("appActivity", appActivity);
//
//        LOGGER.info("Instantiating driver");
//        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
//    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException, ParseException {
        String path = "src/main/resources/capabilities.json";
        URL url = parseJSONFile(path);


        driver = new RemoteWebDriver(url, capabilities);
    }

    private URL parseJSONFile(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject) parser.parse(new FileReader(path));

        JSONArray envs = (JSONArray) config.get("environments");
        Map<String, String> envCapabilities = (Map<String, String>) envs.get(0);
        Iterator<Map.Entry<String, String>> it = envCapabilities.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, String> pair = it.next();
            capabilities.setCapability(pair.getKey(), pair.getValue());
        }

        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> pair = it.next();
            if (capabilities.getCapability(pair.getKey()) == null) {
                capabilities.setCapability(pair.getKey(), pair.getValue());
            }
        }
        String username = System.getenv("BROWSERSTACK_USERNAME");
        if (username == null) {
            username = (String) config.get("username");
        }

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = (String) config.get("access_key");
        }

        String app = System.getenv("BROWSERSTACK_APP_ID");
        if (app != null && !app.isEmpty()) {
            capabilities.setCapability("app", app);
        }

        return new URL("http://" + username + ":" + accessKey + "@" + config.get("server") + "/wd/hub");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    protected String searchProperty(String property) throws IOException {
        InputStream input = new FileInputStream(System.getProperty("user.dir").concat("/src/test/resources/credentials.properties"));

        Properties properties = new Properties();
        properties.load(input);
        String target = properties.getProperty(property);

        input.close();

        return target;
    }

    static class DriverSessionException extends Exception {
        DriverSessionException(String message) {
            super(message);
        }
    }
}
