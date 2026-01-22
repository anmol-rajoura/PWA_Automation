package stepDefinations;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.core.backend.Options;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import reports.ExtentManager;
import utils.AlertHelper;
import utils.DriverFactory;
import utils.Log;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;
    AlertHelper alertHelper;
    Options options;

    @Given("user is on login page")
    public void openLoginPage() {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        alertHelper = new AlertHelper(driver);
        driver.get("https://stackd-dev-2.app.stackd.co.in/login");
        Log.info("Navigated to Login Page");
        alertHelper.dismissAlertIfPresent();
    }

    @When("user enters phone number {string}")
    public void enterCredentials(String phoneNumber) //Created local variable here also because step files access variable directly from feature file
    {
        loginPage.enterUsername(phoneNumber);
        ExtentManager.getTest().pass("Entered Mobile Number Successfully " + phoneNumber);
        Log.info("Entered valid Mobile Number" + phoneNumber);
    }

    @When("user click on send OTP button")
    public void loginMobile() {
        loginPage.sendOTPBtn();
        ExtentManager.getTest().pass("Clicked Send OTP Button Successfully");
        Log.info("Clicked on Send OTP button");
        alertHelper.dismissAlertIfPresent();
        //driver.switchTo().alert().accept();
    }
    
    @When("user enters valid OTP")
    public void loginOtp() {
    	loginPage.enterOTP("123456");
    	ExtentManager.getTest().pass("Entered OTP Successfully");
    	Log.info("User entered OTP successfully");
    	//options.addArguments("--disable-notifications");
    	alertHelper.dismissAlertIfPresent();
    	//driver.switchTo().alert().accept();
    	
    }

    @Then("user should be logged in successfully")
    public void loginSuccess() {
        Assert.assertTrue(loginPage.isHomePageDisplayed());
        ExtentManager.getTest().pass("Login successful");
        Log.info("Login validation completed");
    }
}

