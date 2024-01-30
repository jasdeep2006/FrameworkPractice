@tag1
Feature: Error validation of landing page

   @ErrorValid
  Scenario Outline: Negative test of loggin in
     Given : I landed on Ecommerce website
     When : Log in with <username> and <password>
     Then : "Incorrect email or password." is diplayed as error message
     Examples:
       | username | password |
       | jasdeep3@gmail.com | Gekko@06 |
       | ansha@gmail.com | Iamking@000 |