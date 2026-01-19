package hooks;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import pages.LoginPage;
import reports.ExtentManager;
import utils.DriverFactory;
import utils.Log;

public class Hooks {

    @Before(order = 0)
    public void setup() {
        DriverFactory.initDriver();
        Log.info("Browser launched");
        ExtentManager.startTest("Login Test");
    }
    
 // 🔑 TAG-BASED LOGIN HOOK
    @Before(value = "@requiresLogin", order = 1)
    public void loginBeforeScenario() {

        WebDriver driver = DriverFactory.getDriver();
        LoginPage loginPage = new LoginPage(driver);

        Log.info("Executing login from @requiresLogin hook");

        driver.get("https://stackd-dev-2.app.stackd.co.in/login");

        // ✅ Reuse existing login flow
        loginPage.enterUsername("9650801890");
        loginPage.sendOTPBtn();
        loginPage.enterOTP("123456");

        Assert.assertTrue(
                loginPage.isHomePageDisplayed(),
                "Login failed in @requiresLogin hook"
        );

        Log.info("Login successful via hook");
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
        Log.info("Browser closed");
        ExtentManager.endTest();
    }
}

