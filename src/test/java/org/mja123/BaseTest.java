package org.mja123;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;


public class BaseTest implements Callable<RemoteWebDriver> {
//    protected volatile RemoteWebDriver driver;
//    private volatile DesiredCapabilities capabilities = new DesiredCapabilities();
    protected static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private ExecutorService executorService;

    @BeforeSuite
    public void parallelSetUp(){
        executorService = Executors.newFixedThreadPool(5);
    }
    public RemoteWebDriver setUpDriver() {
        Future<?> result = executorService.submit(this);
        System.out.println(result);
        try {
            System.out.println("here");
            return (RemoteWebDriver) result.get();
//            System.out.println("Driver" + driver.toString());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RemoteWebDriver call() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String path = "src/main/resources/capabilities.json";
//        String device = System.getProperty("device");
        String device = System.getProperty("device");
        JSONParser parser = new JSONParser();
        JSONObject config = null;
        try {
            config = (JSONObject) parser.parse(new FileReader(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Iterator<? extends Map.Entry<String, ?>> it;

        JSONArray envs = (JSONArray) config.get("environments");

        for (Object env : envs) {
            Map<String, String> envCapabilities = (Map<String, String>) env;
            //Iterating through the devices and checking if the device' name is the passed in the device parameter (currentDevice == 1)
            int correctDevice = envCapabilities.entrySet().stream()
                    .filter(k -> k.getKey().equals("device") && k.getValue().equals(device))
                    .toList()
                    .size();

            //If this is true, fill the capabilities with the data in the current json object
            if (correctDevice == 1) {
                it = envCapabilities.entrySet().iterator();
                while(it.hasNext()) {
                    Map.Entry<String, ?> currentCapability = it.next();
                    capabilities.setCapability(currentCapability.getKey(), currentCapability.getValue());
                }
                break;
            }
        }
        Map<String, ?> commonCapabilities = (Map<String, ?>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ?> pair = it.next();
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

        URL url = null;
        try {
            url = new URL("http://" + username + ":" + accessKey + "@" + config.get("server") + "/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("here2");
        return new RemoteWebDriver(url, capabilities);
    }


//    private URL parseJSONFile(String path, String device) throws IOException, ParseException {
//        JSONParser parser = new JSONParser();
//        JSONObject config = (JSONObject) parser.parse(new FileReader(path));
//        Iterator<? extends Map.Entry<String, ?>> it;
//
//        if (device.length() < 5 && Character.isDigit(device.charAt(0))) {
//            selectingDevice(Integer.parseInt(device), config);
//        } else {
//            selectingDevice(device, config);
//        }
//
//        Map<String, ?> commonCapabilities = (Map<String, ?>) config.get("capabilities");
//        it = commonCapabilities.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, ?> pair = it.next();
//            if (capabilities.getCapability(pair.getKey()) == null) {
//                capabilities.setCapability(pair.getKey(), pair.getValue());
//            }
//        }
//        String username = System.getenv("BROWSERSTACK_USERNAME");
//        if (username == null) {
//            username = (String) config.get("username");
//        }
//
//        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
//        if (accessKey == null) {
//            accessKey = (String) config.get("access_key");
//        }
//
//        String app = System.getenv("BROWSERSTACK_APP_ID");
//        if (app != null && !app.isEmpty()) {
//            capabilities.setCapability("app", app);
//        }
//        return new URL("http://" + username + ":" + accessKey + "@" + config.get("server") + "/wd/hub");
//    }
//
//    private void selectingDevice(String device, JSONObject config) {
//        JSONArray envs = (JSONArray) config.get("environments");
//        Iterator<Map.Entry<String, String>> it;
//
//        for (Object env : envs) {
//            Map<String, String> envCapabilities = (Map<String, String>) env;
//            //Iterating through the devices and checking if the device' name is the passed in the device parameter (currentDevice == 1)
//            int correctDevice = envCapabilities.entrySet().stream()
//                    .filter(k -> k.getKey().equals("device") && k.getValue().equals(device))
//                    .toList()
//                    .size();
//
//            //If this is true, fill the capabilities with the data in the current json object
//            if (correctDevice == 1) {
//                it = envCapabilities.entrySet().iterator();
//                while(it.hasNext()) {
//                    Map.Entry<String, ?> currentCapability = it.next();
//                    capabilities.setCapability(currentCapability.getKey(), currentCapability.getValue());
//                }
//                break;
//            }
//        }
//
//        capabilities.asMap().forEach((k,v)-> System.out.println(k + ": " + v));
//    }
//
//    private void selectingDevice(int device, JSONObject config) {
//        JSONArray envs = (JSONArray) config.get("environments");
//        Map<String, String> envCapabilities;
//        Iterator<Map.Entry<String, String>> it;
//
//        if (envs.size() <= device) {
//            envCapabilities = (Map<String, String>) envs.get(0);
//            LOGGER.warn("Device number is bigger than the available devices count. First device is used");
//
//        } else {
//                envCapabilities = (Map<String, String>) envs.get(device);
//        }
//
//        it = envCapabilities.entrySet().iterator();
//
//        while(it.hasNext()) {
//            Map.Entry<String, ?> currentCapability = it.next();
//            capabilities.setCapability(currentCapability.getKey(), currentCapability.getValue());
//        }
//        capabilities.asMap().forEach((k,v)-> System.out.println(k + ": " + v));
//
//    }
    protected String searchProperty(String property) throws IOException {
        InputStream input = new FileInputStream(System.getProperty("user.dir").concat("/src/test/resources/credentials.properties"));

        Properties properties = new Properties();
        properties.load(input);
        String target = properties.getProperty(property);

        input.close();

        return target;
    }
}
