package rahulshettyacademy.pageObject;

import rahulshettyacademy.AbstractClass.abstractClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class OrderConfirmation extends abstractClass {
    WebDriver driver;

    public OrderConfirmation(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//div[@class='cart']//h3")
    List<WebElement> cartProducts;


    public Boolean productCheck(String productName) {

       return cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

    }




}





