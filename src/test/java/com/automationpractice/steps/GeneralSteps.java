package com.automationpractice.steps;

import com.automationpractice.configuration.BasePage;
import com.automationpractice.configuration.CucumberStepDefinition;
import com.automationpractice.ui.pageObjects.AuthenticationPage;
import com.automationpractice.ui.pageObjects.CartPage;
import com.automationpractice.ui.pageObjects.CheckoutPage;
import com.automationpractice.ui.pageObjects.Homepage;

import cucumber.api.java.en.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static com.automationpractice.configuration.BasePage.generateRandomEmail;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertTrue;

@Slf4j
@CucumberStepDefinition
public class GeneralSteps {

    @Autowired
    private Homepage homepage;
    @Autowired
    private AuthenticationPage authenticationPage;
    @Autowired
    private CartPage cartPage;
    @Autowired
    private CheckoutPage checkoutPage;

    @Given("The web app is running")
    public void launchWebApp() {
        open("/");
    }

    @And("I navigate to the \"([^\"]*)\" page")
    public void iNavigateToPage(String page) {
        homepage.navigateToPage(page);
    }

    @When("I try to log in with invalid \"([^\"]*)\" and \"([^\"]*)\"")
    public void logInWithInvalidCredentials(String wrongEmail, String wrongPassword) {
        authenticationPage.login(wrongEmail, wrongPassword);
    }

    @Then("I should receive an error message")
    public void iShouldReceiveAnErrorMessage() {
        assertTrue(authenticationPage.authenticationFailed());
        log.info("Failed to authenticate user.");
    }

    @And("I add a product to the cart")
    public void addProductToCart() {
        homepage.addProductToCart();
    }

    @Then("I proceed to checkout")
    public void proceedToCheckout() {
        homepage.proceedToCheckout();
    }

    @And("I continue to the next part of checkout")
    public void continueToNextPageOfCheckout() {
        cartPage.continueToCheckout();
    }

    @Then("I \"([^\"]*)\" in authentication page")
    public void iAuthenticateOrCreateAccount(String authenticateOrCreateAccount) {
        if (authenticateOrCreateAccount.equals("authenticate"))
            authenticationPage.login();
        else {
            authenticationPage.gotoCreateAccount(BasePage.generatedEmailAddress = generateRandomEmail());
            authenticationPage.createDefaultAccountWithMandatoryFields();
        }
    }

    @Then("I select shipping and continue")
    public void selectShippingAndContinue() {
        checkoutPage.checkTermsOfServiceCheckbox();
        checkoutPage.continueToCheckout();
    }

    @And("I select to pay by \"([^\"]*)\"")
    public void selectToPayBy(String paymentMethod) {
        if(paymentMethod.equals("bank wire"))
            checkoutPage.selectPayByBankwire();
    }

    @And("I confirm the order")
    public void confirmOrder() {
        checkoutPage.confirmOrder();
    }

    @Then("I check that the order was successfully completed")
    public void checkThatOrderWasSuccessfullyCompleted() {
        assertTrue(checkoutPage.orderWasSuccessfullyPlaced());
    }

}
