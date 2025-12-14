package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getReporter() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

    public static void startTest(String testName) {
        test = getReporter().createTest(testName);
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void endTest() {
        getReporter().flush();
    }
}

