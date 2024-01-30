package rahulshettyacademy.Tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import rahulshettyacademy.pageObject.LandingPage;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class StandAloneTestcase {
    public static void main(String[] args) throws IOException {

//        excelDataReader excelData =  new excelDataReader();
        String productName = "ADIDAS ORIGINAL";
        WebDriver driver = new ChromeDriver();
        LandingPage landingPage = new LandingPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client/");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        // Logging in the page
//        driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys(excelData.getDataID().get(1));
        driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Gekko@06");
        driver.findElement(By.xpath("//input[@id='login']")).click();

        //Getting all items and selecting the correct item
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']")));
       List<WebElement> items =  driver.findElements(By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']"));
       WebElement shoes = items.stream().filter(item -> item.findElement(By.tagName("b")).
               getText().equals(productName)).findFirst().orElse(null);
        shoes.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        //Adding second product
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='toast-container']")));
//        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
//
//        List<WebElement> products =  driver.findElements(By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']"));
//        WebElement iphone = products.stream().filter(product -> product.findElement(By.tagName("b")).
//                getText().equals("IPHONE 13 PRO")).findFirst().orElse(null);
//        iphone.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        //Verifying if the product got added in cart or not

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='toast-container']")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        //Verifying correct product in the cart

        List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cart']//h3"));

        Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));

        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();
        
        //Checking out
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("IND");
        List<WebElement> countries = driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']//button"));

        WebElement india = countries.stream().filter(country -> country.getText().equalsIgnoreCase("India")).findFirst().orElse(null);

        india.click();

        driver.findElement(By.xpath("//div[@class='actions']//a")).click();

        //Order confirmation message
        String orderConfirmation = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
        Assert.assertTrue(orderConfirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.quit();
    }
}
