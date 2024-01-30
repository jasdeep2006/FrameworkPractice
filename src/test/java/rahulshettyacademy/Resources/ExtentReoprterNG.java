package rahulshettyacademy.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReoprterNG {

    public static ExtentReports getReporObejct()
    {
        String path = System.getProperty("user.dir")+"//reports//index.html";

        ExtentSparkReporter reporter =new ExtentSparkReporter(path);

        reporter.config().setReportName("Academy Report");
        reporter.config().setDocumentTitle("Testing Automation");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Jasdeep Singh");
        return extent;
    }
}
