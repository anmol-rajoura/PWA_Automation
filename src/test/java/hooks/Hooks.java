package hooks;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.*;
import pages.LoginPage;
import reports.ExtentManager;
import utils.DriverFactory;
import utils.Log;
import utils.Screenshot;

public class Hooks {
	

    // 🔹 Initialize Extent ONCE per test run
    @BeforeAll
    public static void beforeAll() {
        ExtentManager.initReport();
    }

    // 🔹 Before each scenario
    @Before(order = 0)
    public void setup(Scenario scenario) {
        DriverFactory.initDriver();
        Log.info("Browser launched");
        ExtentManager.startTest(scenario.getName());
    }

    // 🔑 TAG-BASED LOGIN HOOK (CI-SAFE)
    @Before(value = "@requiresLogin", order = 1)
    public void loginBeforeScenario(Scenario scenario) {
        try {
            WebDriver driver = DriverFactory.getDriver();
            LoginPage loginPage = new LoginPage(driver);

            Log.info("Executing login from @requiresLogin hook");

            driver.get("https://stackd-dev-2.app.stackd.co.in/login");
            // Temporary jugad for random numbers
            long base = System.currentTimeMillis() % 1000000000L;
            String number = "9" + String.format("%09d", base);

            loginPage.enterUsername(number);
            loginPage.sendOTPBtn();
            loginPage.enterOTP("123456");

            Assert.assertTrue(
                    loginPage.isHomePageDisplayed(),
                    "Login failed in @requiresLogin hook"
            );

            Log.info("Login successful via hook");

        } catch (Exception e) {

            // 🔥 Log hook failure in Extent
            ExtentManager.getTest()
                    .fail("Login failed in @requiresLogin hook");

            ExtentManager.getTest().fail(e);

            throw e; // DO NOT swallow exception
        }
    }

    // 🔹 After each scenario
    @After
    public void tearDown(Scenario scenario) {

        WebDriver driver = DriverFactory.getDriver();

        if (scenario.isFailed()) {

            // ❌ Mark scenario failed
            ExtentManager.getTest()
                    .fail("Scenario failed: " + scenario.getName());

            // 📸 Capture screenshot
            String screenshotPath =
                    Screenshot.captureScreenshot(driver, scenario.getName());

            // 📎 Attach screenshot to Extent
            if (screenshotPath != null) {
                ExtentManager.getTest()
                        .addScreenCaptureFromPath(screenshotPath);
            }

        } else {
            ExtentManager.getTest().pass("Scenario passed");
        }

        DriverFactory.quitDriver();
        Log.info("Browser closed");
    }

    // 🔹 Flush Extent ONCE after all scenarios
    @AfterAll
    public static void afterAll() {
        ExtentManager.flushReport();
    }
}