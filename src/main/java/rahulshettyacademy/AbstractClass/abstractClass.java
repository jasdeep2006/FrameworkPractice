package rahulshettyacademy.AbstractClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rahulshettyacademy.pageObject.CheckOutPage;
import rahulshettyacademy.pageObject.OrderConfirmation;

import java.time.Duration;

public class abstractClass {

    WebDriver driver;
    public abstractClass(WebDriver driver) {
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartButton;
    @FindBy(css = ".totalRow button")
    WebElement submitOrderButton;

    public void waitForElementToAppear(By findBY) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBY));
    }

    public void waitForElementToAppear(WebElement findBY) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(findBY));
    }
    public void waitForElementToDisappear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public OrderConfirmation goToCart()
    {
        cartButton.click();
        OrderConfirmation orderConfirmation = new OrderConfirmation(driver);
        return orderConfirmation;
    }

    public CheckOutPage goToCheckout()
    {
        submitOrderButton.click();
        CheckOutPage checkOutPage =  new CheckOutPage(driver);
        return checkOutPage;
    }


}
