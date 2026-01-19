package stepDefinations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ProfilePage;
import reports.ExtentManager;
import utils.DriverFactory;
import utils.Log;

public class ProfileSteps {
	WebDriver driver;
    ProfilePage profilePage;

    public ProfileSteps() {
        this.driver = DriverFactory.getDriver();
        this.profilePage = new ProfilePage(driver);
    }

    @When("user enters name {string}")
    public void user_enters_name(String name) {
        profilePage.enterName(name);
        ExtentManager.getTest().pass("Entered name Successfully" + name);
        Log.info("Entered name: " + name);
    }

    @When("user enters email {string}")
    public void user_enters_email(String email) {
        profilePage.enterEmail(email);
        ExtentManager.getTest().pass("Entered email Successfully" + email);
        Log.info("Entered email: " + email);
    }
    
    @When("user selects date of birth {string}")
    public void user_selects_date_of_birth(String dob) {
        profilePage.selectDateOfBirth(dob);
        ExtentManager.getTest().pass("Entered dob Successfully");
        Log.info("Entered dob" + dob);
    }

    @When("user selects gender radio button")
    public void user_selects_gender_radio_button() {
        profilePage.selectRadioButton();
        ExtentManager.getTest().pass("Selected gender Successfully");
        Log.info("Selected gender radio button");
    }

    @When("user clicks on Save and Continue button")
    public void user_clicks_on_save_and_continue_button() {
        profilePage.clickSaveAndContinue();
        ExtentManager.getTest().pass("Clicked Save&Continue button Successfully");
        Log.info("Clicked on Save and Continue button");
    }

    @Then("profile should be created successfully")
    public void profile_should_be_created_successfully() {
        Assert.assertTrue(
                profilePage.isProfilePageDisplayed(),
                "Profile creation failed: Profile page not visible"
        );
        ExtentManager.getTest().pass("Profile created Successfully");
        Log.info("Profile created successfully");
    }

//    @When("user clicks on Delete profile button")
//    public void user_clicks_on_delete_profile_button() {
//        profilePage.clickDeleteAccount();
//        Log.info("Clicked on Delete Profile button");
//    }

//    @Then("profile should be deleted successfully")
//    public void profile_should_be_deleted_successfully() {
//        // Depending on app behavior, adjust validation
//        Log.info("Profile deleted successfully");
//    }

}
