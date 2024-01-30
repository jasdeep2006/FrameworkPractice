package rahulshettyacademy.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractClass.abstractClass;

import java.util.List;

public class ProductCatalouge extends abstractClass {
    WebDriver driver;

    public ProductCatalouge(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']")
    List<WebElement> productList;

    @FindBy(css = ".ng-animating")
    WebElement spinner;


    By productList1 = By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By confirmationToast = By.cssSelector("div[id='toast-container']");

    public List<WebElement> getProductList() {
        waitForElementToAppear(productList1);
        return productList;
    }

    public WebElement getProductByName(String productName) {
        WebElement shoes = getProductList().stream().filter(item -> item.findElement(By.tagName("b")).
                getText().equals(productName)).findFirst().orElse(null);
        return shoes;
    }

    public void addProductToCart(String productName) {
        WebElement shoes = getProductByName(productName);
        shoes.findElement(addToCart).click();
        waitForElementToAppear(confirmationToast);
        waitForElementToDisappear(spinner);
    }


}
