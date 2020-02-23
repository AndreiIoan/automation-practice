package com.automationpractice.steps;

import com.automationpractice.configuration.BasePage;
import com.automationpractice.configuration.CucumberStepDefinition;
import com.automationpractice.ui.pageObjects.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.automationpractice.configuration.BasePage.errorNotPresent;
import static com.automationpractice.configuration.BasePage.generateRandomEmail;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.*;

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
    @Autowired
    private MyAccountPage myAccountPage;
    @Autowired
    private ProductPage productPage;
    @Autowired
    private QuickViewPage quickViewPage;
    @Autowired
    private SearchPage searchPage;
    @Autowired
    private CategoryPage categoryPage;

    @Given("homepage is opened")
    public void launchWebApp() {
        open("/");
        if(homepage.userIsAuthenticated())
            homepage.logout();
    }

    @And("The user navigates to the (.*) page")
    public void iNavigateToPage(String page) {
        homepage.navigateToPage(page);
    }

    @When("Tries to log in with (.*) (.*) and (.*)")
    public void logInWithInvalidCredentials(String validOrInvalid, String email, String password) {
        log.info("Logging in with {} credentials.", validOrInvalid);
        authenticationPage.login(email, password);
    }

    @Then("the user (.*) logged in")
    public void theUserShouldOrShouldntBeSuccessfullySignedIn(String authenticated) {
        if(authenticated.equals("isn't")) {
            assertTrue(authenticationPage.authenticationFailed());
            log.info("Failed to authenticate user.");
        } else {
            assertTrue(myAccountPage.amOnAccountPage());
            log.info("Successfully authenticated user.");
        }
    }

    @And("the user adds a product to the cart from (.*)")
    public void addProductToCart(String page) {
        if(page.equalsIgnoreCase("homepage")) {
            homepage.quickViewProduct();
            quickViewPage.switchToQuickViewFrame();
            quickViewPage.selectSize("M");
            quickViewPage.selectRandomNotSelectedColor();
            quickViewPage.addToCart();
        }
        else
            categoryPage.addProductToCart();
    }

    @And("finishes the checkout process through (.*)")
    public void completeCheckoutProcess(String paymentMethod) {
        homepage.proceedToCheckout();
        cartPage.continueToCheckout();
        authenticationPage.gotoCreateAccount(BasePage.generatedEmailAddress = generateRandomEmail());
        authenticationPage.createDefaultAccountWithMandatoryFields();
        assertTrue(errorNotPresent());
        cartPage.continueToCheckout();
        checkoutPage.checkTermsOfServiceCheckbox();
        checkoutPage.continueToCheckout();
        if(paymentMethod.equals("bank wire"))
            checkoutPage.selectPayByBankwire();
    }

    @Then("the order should be successfully placed")
    public void continueToNextPageOfCheckout() {
        checkoutPage.confirmOrder();
        assertTrue(checkoutPage.orderWasSuccessfullyPlaced());
    }

    @Then("user (.*) in authentication page")
    public void authenticateOrCreateAccount(String authenticateOrCreateAccount) {
        if (authenticateOrCreateAccount.equals("authenticates"))
            authenticationPage.login("andreitest@test.com", "password");
        else {
            authenticationPage.gotoCreateAccount(BasePage.generatedEmailAddress = generateRandomEmail());
            authenticationPage.createDefaultAccountWithMandatoryFields();
            assertTrue(errorNotPresent());
        }
    }

    @Then("an account should successfully be created")
    public void accountSuccessfullyCreated() {
        assertTrue(myAccountPage.amOnAccountPage());
    }

    @When("the user searches for an existing item (.*)")
    public void userSearchesForAnExistingItem(String item) {
        homepage.search(item);
    }

    @And("the user should be taken to the search page with the item")
    public void confirmOrder() {
        assertEquals(searchPage.searchPageTitle(), "Search");
    }

    @Then("the category should have items")
    public void categoryShouldHaveItems() {
        assertTrue(categoryPage.itemsArePresentInCategory());
    }

    @Then("the product should be added to the cart")
    public void productIsAddedToCart() {
        categoryPage.continueShopping();
        categoryPage.gotoCartPage();
        assertFalse(cartPage.cartIsEmpty());
    }

    @When("the user goes to the product page")
    public void navigateToProductPage() {
        homepage.gotoProductPage();
    }

    @Then("the user can (.*) product quantity")
    public void userCanIncreaseOrDecreaseProductQuantity(String increaseOrDecrease) {
        if(increaseOrDecrease.equals("increase"))
            productPage.increaseQuantity();
        else
            productPage.decreaseQuantity();
    }

}
