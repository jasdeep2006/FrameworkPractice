package rahulshettyacademy.stepDefination;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObject.*;

import java.io.IOException;
import java.util.List;

public class StepDefinationImplementation extends BaseTest {

    public LandingPage landingPage;
    public  ProductCatalouge productCatalouge;
    public ConfirmationPage confirmationPage;
    @Given("I landed on Ecommerce website")
    public void iLandedOnEcommerceWebsite() throws IOException {
        landingPage = launchApplicationPage();
    }


    @Given("^: Log in with (.+) and (.+)$")
    public void logInWithUsernameAndPassword(String username,String password) {
        productCatalouge = landingPage.LoginApplication(username,password);
    }


    @When("^: I want to add (.+) in cart$")
    public void iWantToAddProductInCart(String productName) {
        List<WebElement> products = productCatalouge.getProductList();
        productCatalouge.addProductToCart(productName);

    }


    @And("^: Checkout the (.+) and submit the order$")
    public void checkoutTheProductAndSubmitTheOrder(String productName) {
        OrderConfirmation orderConfirmation = productCatalouge.goToCart();
        Boolean match = orderConfirmation.productCheck(productName);
        Assert.assertTrue(match);
        CheckOutPage checkOutPage = orderConfirmation.goToCheckout();
        confirmationPage = checkOutPage.placeOrder();

    }


    @Then(": {string} message is displayed on ConfirmationPage")
    public void messageIsDisplayedOnConfirmationPage(String string) {

        String orderConfirmationMessage = confirmationPage.verifyorderConfirmationMessage();
        Assert.assertTrue(orderConfirmationMessage.equalsIgnoreCase(string));


    }


    @Then(": {string} is diplayed as error message")
    public void isDiplayedAsErrorMessage(String string) {
        Assert.assertEquals(string,landingPage.getErrorMessage());
    }
}
