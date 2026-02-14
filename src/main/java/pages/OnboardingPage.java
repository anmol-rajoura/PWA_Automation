package pages;


import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class OnboardingPage {

    WebDriver driver;

    public OnboardingPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By gotItBtn = By.xpath("//button[@class='got-it-btn']");
    By continueWithBrowserBtn = By.xpath("//div[@class='text2']");
    By anotherGotItBtn = By.xpath("//button[normalize-space()='Got it']");
    By searchMachineHeader = By.xpath("//span[normalize-space()='Search Machine']");

    public void completeOnboarding() {

    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // First Got It
        wait.until(ExpectedConditions.elementToBeClickable(gotItBtn)).click();

        // Continue with Browser
        wait.until(ExpectedConditions.elementToBeClickable(continueWithBrowserBtn)).click();

        // Second Got It
        wait.until(ExpectedConditions.elementToBeClickable(anotherGotItBtn)).click();
    }

    public boolean isSearchMachineDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(searchMachineHeader)
            ).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}



