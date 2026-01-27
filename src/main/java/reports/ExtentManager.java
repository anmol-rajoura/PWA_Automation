package reports;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // 🔹 Initialize report ONCE per test run
    public static synchronized void initReport() {
        if (extent == null) {
            new File("target").mkdirs(); // ensures target exists in CI

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("target/ExtentReport.html");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
    }

    // 🔹 Create test per scenario (thread-safe)
    public static synchronized void startTest(String testName) {
        if (extent == null) {
            initReport(); // 🔥 ensures report exists before test creation
        }

        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);

        // 🔥 CRITICAL FOR CI:
        // Ensures test node is visible even if scenario fails early
        extentTest.info("Scenario started: " + testName);
    }

    // 🔹 Get current scenario test
    public static ExtentTest getTest() {
        return test.get();
    }

    // 🔹 Flush report ONCE after all scenarios
    public static synchronized void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
