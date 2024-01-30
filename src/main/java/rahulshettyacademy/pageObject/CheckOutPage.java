package rahulshettyacademy.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractClass.abstractClass;

import java.util.List;

public class CheckOutPage extends abstractClass {
    WebDriver driver ;

    public CheckOutPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement countryDropdown;

    @FindBy(xpath = "//section[@class='ta-results list-group ng-star-inserted']//button")
    List<WebElement> countryList;

    @FindBy(xpath = "//div[@class='actions']//a")
    WebElement placeOrderButton;



    public ConfirmationPage placeOrder() {

        countryDropdown.sendKeys("IND");
        WebElement india = countryList.stream().filter(country -> country.getText().equalsIgnoreCase("India")).
                findFirst().orElse(null);
        india.click();
        placeOrderButton.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }




}
