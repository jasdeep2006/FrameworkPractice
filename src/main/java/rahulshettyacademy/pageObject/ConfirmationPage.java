package rahulshettyacademy.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractClass.abstractClass;

public class ConfirmationPage extends abstractClass {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//h1[@class='hero-primary']")
    WebElement orderConfirmationMessageText;

    public String verifyorderConfirmationMessage() {
         String orderConfirmation = orderConfirmationMessageText.getText();
        return orderConfirmation;
    }
}
