package rahulshettyacademy.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractClass.abstractClass;

public class LandingPage extends abstractClass {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='userEmail']")
    WebElement userLoginID;

    @FindBy(xpath = "//input[@id='userPassword']")
    WebElement userPassword;

    @FindBy(xpath = "//input[@id='login']")
    WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class,'flyInOut')]")
    WebElement errorMessage;

    public void AppURL() {
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public ProductCatalouge LoginApplication(String id, String password) {
        userLoginID.sendKeys(id);
        userPassword.sendKeys(password);
        loginButton.click();

        ProductCatalouge productCatalouge = new ProductCatalouge(driver);
        return productCatalouge;
    }

    public String getErrorMessage()
    {
        waitForElementToAppear(errorMessage);
        String error = errorMessage.getText();
        return error;
    }

}
