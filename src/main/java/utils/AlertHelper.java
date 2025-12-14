package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class AlertHelper {
	WebDriver driver;

    public AlertHelper(WebDriver driver) {
        this.driver = driver;
    }

    // Common reusable alert handler
    public boolean acceptAlertIfPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public boolean dismissAlertIfPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
