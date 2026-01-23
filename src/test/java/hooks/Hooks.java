package hooks;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.*;
import pages.LoginPage;
import reports.ExtentManager;
import utils.DriverFactory;
import utils.Log;

public class Hooks {

    // 🔹 Initialize Extent ONCE
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

    // 🔑 TAG-BASED LOGIN HOOK
    @Before(value = "@requiresLogin", order = 1)
    public void loginBeforeScenario() {

        WebDriver driver = DriverFactory.getDriver();
        LoginPage loginPage = new LoginPage(driver);

        Log.info("Executing login from @requiresLogin hook");

        driver.get("https://stackd-dev-2.app.stackd.co.in/login");
        loginPage.enterUsername("9650801890");
        loginPage.sendOTPBtn();
        loginPage.enterOTP("123456");

        Assert.assertTrue(
                loginPage.isHomePageDisplayed(),
                "Login failed in @requiresLogin hook"
        );

        Log.info("Login successful via hook");
    }

    // 🔹 After each scenario
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            ExtentManager.getTest()
                .fail("Scenario failed: " + scenario.getName());
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
