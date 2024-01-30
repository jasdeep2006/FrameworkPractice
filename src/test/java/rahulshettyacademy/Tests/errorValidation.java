package rahulshettyacademy.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObject.CheckOutPage;
import rahulshettyacademy.pageObject.ConfirmationPage;
import rahulshettyacademy.pageObject.OrderConfirmation;
import rahulshettyacademy.pageObject.ProductCatalouge;

import java.io.IOException;
import java.util.List;

public abstract class errorValidation extends BaseTest {

    @Test
    public void submitOrder() throws IOException {

        String productName = "ADIDAS ORIGINAL";
        String id = "jasdeep3@gmail.com";
        String password = "Gekko@06";

        ProductCatalouge productCatalouge = landingPage.LoginApplication(id, password);
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());


    }


}
