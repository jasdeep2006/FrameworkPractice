package rahulshettyacademy.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import rahulshettyacademy.pageObject.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class BaseTest {


    public WebDriver driver;
    public LandingPage landingPage;


    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("D:/SeleniumFramework/src/test/java/rahulshettyacademy/Resources/GlobalData.properties");
        prop.load(fis);
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
//        String browser = prop.getProperty("browser");

        Map<String, String> deviceMobEmu = new HashMap<>();
        deviceMobEmu.put("deviceName", "iPad Pro");


        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.setExperimentalOption("mobileEmulation", deviceMobEmu);

//                chromeOptions.addArguments("headless");

            driver = new ChromeDriver(chromeOptions);

        }
        if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public List<HashMap<String, String>> getJSONDataToMap() throws IOException {

        //JSON to String
        String jsonContent = FileUtils.readFileToString(new File(
                        (System.getProperty("user.dir")) + "//src//test//java//rahulshettyacademy//data//DataFile.json"),
                StandardCharsets.UTF_8);

        //String to Hashmap

        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> data = objectMapper.readValue(jsonContent, new TypeReference<List<HashMap
                <String, String>>>() {
        });
        return data;

    }

//    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
//        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
//        File file = new File("D:/TCscreenshots" + testCaseName + ".png");
//        FileUtils.copyFile(source, file);
//        return "D:/TCscreenshots" + testCaseName + ".png";
//    }

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        String screenshotPath = null;
        try {
            //take screenshot and save it in a file
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //copy the file to the required path
            File destinationFile = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
            FileHandler.copy(sourceFile, destinationFile);
            String[] relativePath = destinationFile.toString().split("reports");
            screenshotPath = ".\\" + relativePath[1];
        } catch (Exception e) {
            System.out.println("Failure to take screenshot " + e);
        }
        return screenshotPath;
    }

    @BeforeMethod
    public LandingPage launchApplicationPage() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.AppURL();
        return landingPage;

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}

