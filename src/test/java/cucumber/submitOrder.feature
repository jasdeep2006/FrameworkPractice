@tag1
Feature: Purchase order from website

  Background:
    Given I landed on Ecommerce website

  @tag2
  Scenario Outline: Positive test of submitting order
    Given : Log in with <username> and <password>
    When : I want to add <product> in cart
    And : Checkout the <product> and submit the order
    Then : "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
    Examples:
      | username | password | product |
      | jasdeep123@gmail.com | Gekko@06 | ZARA COAT 3 |
      | anshika@gmail.com | Iamking@000 | ADIDAS ORIGINAL |



