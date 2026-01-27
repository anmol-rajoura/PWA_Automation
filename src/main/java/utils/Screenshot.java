package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

    public static String captureScreenshot(WebDriver driver, String scenarioName) {
        try {
            String screenshotDir = "target/screenshots";
            Files.createDirectories(Paths.get(screenshotDir));

            // 🔥 Unique identifiers (Java 21 safe)
            String timestamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());

            String uuid = UUID.randomUUID().toString().substring(0, 8);

            String safeName =
                    scenarioName.replaceAll("[^a-zA-Z0-9]", "_");

            String fileName =
                    safeName + "_" + timestamp + "_" + uuid + ".png";

            String fullPath = screenshotDir + "/" + fileName;

            File source =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            Files.copy(source.toPath(), Paths.get(fullPath));

            // 🔑 Relative path for Extent
            return "screenshots/" + fileName;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
