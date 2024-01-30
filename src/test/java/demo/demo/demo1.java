package demo.demo;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class demo1 {


@BeforeTest
public void config()
{
String path =System.getProperty("user.dir")+"//reports/index.html";
    ExtentSparkReporter reporter = new ExtentSparkReporter(path);

    ExtentReports extentReports = new ExtentReports();
    extentReports.attachReporter(reporter);
}


    @Test
    public void initialDemo()
    {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client/");
        System.out.println(driver.getTitle());

    }
}
