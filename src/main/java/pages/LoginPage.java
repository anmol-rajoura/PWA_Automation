package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;	 
    

    By username = By.xpath("//div[@class='input-wrapper']//input");
    By sendBtn = By.xpath("//button[normalize-space()='Send OTP']");
    By otpBoxes = By.xpath("//input[contains(@aria-label,'')]"); // Please enter OTP character
    By loginBtn = By.id("login");
    By createProfileHeader = By.xpath("//h2[text()='Create Profile']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String phoneNumber) {
        driver.findElement(username).sendKeys(phoneNumber);
        //driver.findElement(username).clear();
    }

    public void sendOTPBtn() {
        driver.findElement(sendBtn).click();
      
    }

    public void enterOTP(String otp) {
    	 List<WebElement> boxes = driver.findElements(otpBoxes);

         // Loop through all input fields and send digits
         for (int i = 0; i < otp.length(); i++) {
             boxes.get(i).sendKeys(String.valueOf(otp.charAt(i)));
         }
    }

    public boolean isHomePageDisplayed() {
//    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        return wait.until(ExpectedConditions.urlContains("/profile"));
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            return wait.until(
                ExpectedConditions.or(
                    ExpectedConditions.urlContains("/profile"),
                    ExpectedConditions.visibilityOfElementLocated(createProfileHeader)
                )
            );
        } catch (TimeoutException e) {
            return false;
        }
    }
}


    
