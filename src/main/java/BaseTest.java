import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class BaseTest {
    public AndroidDriver<AndroidElement> driver;

    @BeforeMethod(alwaysRun = true)
    @org.testng.annotations.Parameters(value={"deviceIndex", "environment"})
    protected AndroidDriver getDriver(String deviceIndex, String env) throws ParseException, IOException {
        if(env.equalsIgnoreCase("local")){
            driver = getLocalDriver(deviceIndex);
        }
        else if (env.equalsIgnoreCase("cloud")){
            driver = getCloudDriver(deviceIndex);
        }
        else {
            System.out.println("Environment is not valid!");
        }
        return driver;
    }

    private AndroidDriver getLocalDriver(String deviceIndex) throws IOException, ParseException {
        DesiredCapabilities caps = new DesiredCapabilities();

        //Read the config.json file and parse the content to a json object
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/config.json"));

        //Set capabilities for APP under testing (e.g. appPackage, appActivity)
        JSONObject app = (JSONObject) config.get("app");
        Map<String, String> appCaps = (Map<String,String>) app;
        Iterator it = appCaps.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            caps.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        //Set capabilities for DEVICES (e.g. deviceName, udid, platformName, platformVersion)
        JSONArray capabilities = (JSONArray) config.get("capabilities");
        Map<String, String> envCapabilities = (Map<String, String>) capabilities.get(Integer.parseInt(deviceIndex));
        it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            caps.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        //Instantiate the driver
        try{
            driver = new AndroidDriver(new URL(config.get("serverUrl").toString()), caps);
        } catch (Exception e){
            System.out.println("Unable to create Android Driver! " + e.getMessage());
        }

        return driver;
    }

    private AndroidDriver getCloudDriver(String deviceIndex) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/browserstack-config.json"));
        JSONArray envs = (JSONArray) config.get("environments");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(Integer.parseInt(deviceIndex));
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(capabilities.getCapability(pair.getKey().toString()) == null){
                capabilities.setCapability(pair.getKey().toString(), pair.getValue());
            }
        }

        String username = System.getenv("BROWSERSTACK_USERNAME");
        if(username == null) {
            username = (String) config.get("username");
        }

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if(accessKey == null) {
            accessKey = (String) config.get("access_key");
        }

        String app = System.getenv("BROWSERSTACK_APP_ID");
        if(app != null && !app.isEmpty()) {
            capabilities.setCapability("app", app);
        }

        driver = new AndroidDriver(new URL("http://"+username+":"+accessKey+"@"+config.get("server")+"/wd/hub"), capabilities);

        return driver;
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        driver.quit();
    }

}
