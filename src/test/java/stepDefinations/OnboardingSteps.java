package stepDefinations;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Then;
import pages.OnboardingPage;
import utils.DriverFactory;
import utils.Log;
import reports.ExtentManager;

public class OnboardingSteps {

    WebDriver driver;
    OnboardingPage onboardingPage;

    public OnboardingSteps() {
        this.driver = DriverFactory.getDriver();
        this.onboardingPage = new OnboardingPage(driver);
    }

    @Then("user completes onboarding successfully")
    public void user_completes_onboarding_successfully() {

        onboardingPage.completeOnboarding();

        ExtentManager.getTest().pass("Completed onboarding steps successfully");
        Log.info("Completed onboarding steps successfully");

        Assert.assertTrue(
                onboardingPage.isSearchMachineDisplayed(),
                "Onboarding failed: Search Machine page not visible"
        );

        ExtentManager.getTest().pass("User successfully landed on Search Machine screen");
        Log.info("User successfully landed on Search Machine screen");
    }
}
