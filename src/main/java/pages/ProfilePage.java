package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {
	WebDriver driver;

    // ===== Locators =====
    By nameInput = By.xpath("//input[@type='text']");
    By emailInput = By.xpath("//input[@type='email']");
    By dobInput = By.xpath("//input[@placeholder='Date of birth']"); ////input[@placeholder='Date of birth']
    By radioButton = By.xpath("//span[normalize-space()='Male']");//("//label[@class='MuiFormControlLabel-root MuiFormControlLabel-labelPlacementEnd css-1hfc2jt']");//("//input[@value='Male']"); //input[@value='Male']
    By saveAndContinueBtn = By.xpath("//button[text()='Save & Continue']");
    //By deleteAccountBtn = By.xpath("//div[@class='delete-account-button']");

    // Optional success validation element
    By profileSavedMsg = By.xpath("//*[contains(text(),'Profile')]");

    // ===== Constructor =====
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    // ===== Actions =====

    public void enterName(String name) {
        WebElement nameField = driver.findElement(nameInput);
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(emailInput);
        emailField.clear();
        emailField.sendKeys(email);
    }
    
    public void selectDateOfBirth(String dob) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement dobField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        (dobInput)
                )
        );

        dobField.click();
        dobField.clear();
        dobField.sendKeys(dob);

        // ✅ CLOSE THE CALENDAR POPUP
        dobField.sendKeys(Keys.ESCAPE);
    }


    public void selectRadioButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement radio = wait.until(
                ExpectedConditions.elementToBeClickable(radioButton)
        );

        if (!radio.isSelected()) {
            radio.click();
        }
    }


    public void clickSaveAndContinue() {
        driver.findElement(saveAndContinueBtn).click();
    }

//    public void clickDeleteAccount() {
//        driver.findElement(deleteAccountBtn).click();
//    }

    // ===== Validations =====

    public boolean isProfilePageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(saveAndContinueBtn)
            );
            return element.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

}
