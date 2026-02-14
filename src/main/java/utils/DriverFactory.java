package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver() {

        String browser = System.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
            }

            driver.set(new ChromeDriver(options));

        } else if (browser.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-notifications");
            if (headless) {
                options.addArguments("--headless");
            }

            driver.set(new FirefoxDriver(options));

        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
