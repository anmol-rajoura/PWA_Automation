package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import reports.ExtentManager;
import utils.DriverFactory;
import utils.Log;

public class Hooks {

    @Before
    public void setup() {
        DriverFactory.initDriver();
        Log.info("Browser launched");
        ExtentManager.startTest("Login Test");
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
        Log.info("Browser closed");
        ExtentManager.endTest();
    }
}

